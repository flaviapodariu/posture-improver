package com.licenta.postureimprover.screens.viewmodels

import android.content.Context
import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.domain.FrameAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    var selector: CameraSelector,
    var provider: ProcessCameraProvider,
    var preview: Preview,
    var analyzer: FrameAnalyzer,
    var executor: ExecutorService
): ViewModel() {

    fun changeSelector(cameraSelector: CameraSelector){
        selector = cameraSelector
    }

    fun getImageAnalysis(context: Context, getLandmarks: (List<PoseLandmark>) -> Unit): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetResolution(Size(1080, 2400))   // ??? height
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build().also{
                analyzer.context = context
                analyzer.returnLandmarks = getLandmarks
                it.setAnalyzer(executor, analyzer)
            }
    }
}