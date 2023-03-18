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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.data.api.services.CaptureService
import com.licenta.postureimprover.domain.FrameAnalyzer
import com.licenta.postureimprover.domain.models.PostureCapture
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
    private var captureService: CaptureService
): ViewModel() {

    var timerRuns: Boolean by mutableStateOf(true)
    fun changeSelector(cameraSelector: CameraSelector){
        selector = cameraSelector
    }

    fun sendPosture(capture: PostureCapture?) {
        viewModelScope.launch {
            if (capture != null) {
                captureService.sendCapture(capture)
            }
            else{
                println("nahhh")
            }

        }
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