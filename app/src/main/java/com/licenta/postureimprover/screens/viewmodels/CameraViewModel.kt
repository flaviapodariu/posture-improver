package com.licenta.postureimprover.screens.viewmodels

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.ViewModel
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

    fun getImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
            .build().also{it ->
                it.setAnalyzer(executor, analyzer)
            }
    }
}