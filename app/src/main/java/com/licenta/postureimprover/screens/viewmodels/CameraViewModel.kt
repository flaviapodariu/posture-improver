package com.licenta.postureimprover.screens.viewmodels

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
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.util.FrameAnalyzer
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
    var captureService: CaptureService,
    var prefs: SharedPreferences
): ViewModel() {

    private val token = prefs.getString("jwt", "")!!

    var timerRuns: Boolean by mutableStateOf(true)
    var isErrorDialogShowing: Boolean by mutableStateOf(false)

    fun changeSelector(cameraSelector: CameraSelector){
        selector = cameraSelector
    }

    fun sendPosture(capture: CaptureReq) : Task<Boolean>? {
        var result: Task<Boolean>? = null
        viewModelScope.launch {
            captureService.insertCapture(capture, token)?.let {
                result = it
            }
            println("$capture somecapture please")
        }
        return result
    }

    fun getImageAnalysis(
        context: Context,
        getLandmarks: (List<PoseLandmark>) -> Unit,
        getPostureCapture: (CaptureReq) -> Unit
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