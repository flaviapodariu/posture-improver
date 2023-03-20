package com.licenta.postureimprover.screens.viewmodels

import android.content.Context
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
import com.licenta.postureimprover.data.api.dto.WorkoutRes
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.util.FrameAnalyzer
import com.licenta.postureimprover.data.models.PostureCapture
import dagger.hilt.android.lifecycle.HiltViewModel
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
    var captureService: CaptureService
): ViewModel() {

    var timerRuns: Boolean by mutableStateOf(true)
    var captureSent: Boolean by mutableStateOf(false)
    var isErrorDialogShowing: Boolean by mutableStateOf(false)

    fun changeSelector(cameraSelector: CameraSelector){
        selector = cameraSelector
    }

    fun sendPosture(capture: PostureCapture) : Task<WorkoutRes>? {
        var workout: Task<WorkoutRes>? = null
        viewModelScope.launch {
            captureService.sendCapture(capture)?.let {
                workout = it
            }
            println("$capture somecapture please")
        }
        captureSent = true
        return workout
    }

    fun getImageAnalysis(
        context: Context,
        getLandmarks: (List<PoseLandmark>) -> Unit,
        getPostureCapture: (PostureCapture) -> Unit
    ): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetResolution(Size(1080, 2400))   // ??? height
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build().also{
                analyzer.context = context
                analyzer.returnLandmarks = getLandmarks
                analyzer.returnPostureCapture = getPostureCapture
                it.setAnalyzer(executor, analyzer)
            }
    }
}