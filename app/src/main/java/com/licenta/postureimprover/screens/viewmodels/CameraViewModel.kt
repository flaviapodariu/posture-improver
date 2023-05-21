package com.licenta.postureimprover.screens.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.R
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.dto.request.asCaptureEntity
import com.licenta.postureimprover.data.api.dto.response.WorkoutRes
import com.licenta.postureimprover.data.api.services.CaptureApi
import com.licenta.postureimprover.data.repositories.CaptureRepository
import com.licenta.postureimprover.data.repositories.ExercisesRepository
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.util.FrameAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    var selector: CameraSelector,
    var provider: ProcessCameraProvider,
    var preview: Preview,
    var analyzer: FrameAnalyzer,
    var executor: ExecutorService,
    var captureApi: CaptureApi,
    val captureRepo: CaptureRepository,
    val exerciseRepo: ExercisesRepository,
    var prefs: SharedPreferences
): ViewModel() {

    private val token = prefs.getString("jwt", "")!!

    var timerRuns: Boolean by mutableStateOf(false)
    var timerSeconds: Int by mutableStateOf(5)
    var timerIcon: Int by mutableStateOf(R.drawable.round_timer_40)

    var isErrorDialogShowing: Boolean by mutableStateOf(false)
    var settingTimerOption: Boolean by mutableStateOf(false)
    var lens: Int by mutableStateOf(CameraSelector.LENS_FACING_FRONT)

    var shutterFired = false
    var onClickTimerIcon = { settingTimerOption = true }

    @SuppressLint("RestrictedApi")
    fun changeSelector() {
        if(selector.lensFacing == CameraSelector.LENS_FACING_FRONT) {
            selector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            lens = CameraSelector.LENS_FACING_BACK
            timerIcon = R.drawable.round_timer_40
            onClickTimerIcon = {}
        }
        else {
            selector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build()
            lens = CameraSelector.LENS_FACING_FRONT
            onClickTimerIcon = { settingTimerOption = true }
        }
    }

    fun shutterPressed() {
        if(lens == CameraSelector.LENS_FACING_FRONT)
            timerRuns = true
        shutterFired = true

    }

    fun toggleTimerOptionsVisibility() {
        settingTimerOption = !settingTimerOption
    }

    fun selectTimerSeconds(seconds: Int) {
        timerSeconds = seconds

        when(seconds){
            5 -> timerIcon = R.drawable.timer_5s
            10 -> timerIcon = R.drawable.timer_10s
            15 -> timerIcon = R.drawable.timer_15s
        }
    }

    fun sendPostureAsync(capture: CaptureReq) : Deferred<Task<Boolean>?> {
        val defferedResult = viewModelScope.async {
           captureApi.insertCapture(capture, token)
        }
        return defferedResult
    }

    fun saveCaptureLocally(capture: CaptureReq) {
        val captureEntity = capture.asCaptureEntity()
        viewModelScope.launch{
            captureRepo.saveCaptureLocally(captureEntity)

        }
    }
    fun saveWorkoutLocally(exercises: List<WorkoutRes>) {
        viewModelScope.launch{
            exerciseRepo.saveExercisesLocally(exercises)
        }
    }

    fun getWorkoutFromCapturesAsync(capture: CaptureReq) : Deferred<Task<List<WorkoutRes>>?>{
        val defferedRes = viewModelScope.async {
            captureApi.getWorkoutFromCaptures(capture)
        }
        return defferedRes
    }

    fun getImageAnalysis(
        context: Context,
        getLandmarks: (List<PoseLandmark>) -> Unit,
        getPostureCapture: (CaptureReq) -> Unit
    ): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetResolution(Size(1080, 2400))   // ??? height
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build().also {
                analyzer.context = context
                analyzer.returnLandmarks = getLandmarks
                analyzer.returnPostureCapture = getPostureCapture
                it.setAnalyzer(executor, analyzer)
            }
    }
}