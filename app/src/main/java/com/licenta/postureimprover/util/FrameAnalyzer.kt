package com.licenta.postureimprover.util

import android.content.Context
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase.DetectorMode
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import timber.log.Timber
import javax.inject.Inject


class FrameAnalyzer @Inject constructor(): ImageAnalysis.Analyzer {

    private val options = AccuratePoseDetectorOptions.Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()

    lateinit var context: Context
    lateinit var returnLandmarks: (List<PoseLandmark>) -> Unit
    lateinit var returnPostureCapture: (CaptureReq) -> Unit


    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val poseDetector = PoseDetection.getClient(options)
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            Timber.tag("imageinput").d("${imageProxy.width} ${inputImage.height}")
            poseDetector.process(inputImage)
                .addOnSuccessListener { landmarks ->
                    val bodyLandmarks = landmarks.allPoseLandmarks
                    // if person was detected list is not empty
                    if (bodyLandmarks.isNotEmpty()) {
                        returnLandmarks(bodyLandmarks)
//                        Timber.tag("landmarkType").d(landmarksToString(bodyLandmarks))
                        val capture = checkPosture(bodyLandmarks)
                        Timber.tag("capturez").d("${capture.lordosis}, ${capture.headForward},  ${capture.roundedShoulders}")
//                        returnPostureCapture(capture)
                        imageProxy.close()
                    } else {
                        Toast.makeText(
                            context,
                            "Person is not inside capture screen!",
                            Toast.LENGTH_SHORT
                        ).show()
                        imageProxy.close()
                    }
                }
                    .addOnFailureListener { error ->
                        Timber.tag("Detection").d("${error.stackTrace}")
                        imageProxy.close()
                    }

        }
    }

}







