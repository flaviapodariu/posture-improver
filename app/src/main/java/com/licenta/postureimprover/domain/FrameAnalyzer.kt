package com.licenta.postureimprover.domain

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import timber.log.Timber
import javax.inject.Inject


class FrameAnalyzer @Inject constructor(): ImageAnalysis.Analyzer {

    private val options = AccuratePoseDetectorOptions.Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val poseDetector = PoseDetection.getClient(options)
        val mediaImage = imageProxy.image
        if (mediaImage != null){
            val inputImage = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            poseDetector.process(inputImage)
                .addOnSuccessListener { landmarks ->
                    val bodyLandmarks = landmarks.allPoseLandmarks
                    getResults(bodyLandmarks)
                    imageProxy.close()
                }
                .addOnFailureListener { error ->
                    Timber.tag("Detection").d("${error.stackTrace}")
                }


        }
    }
}

fun FrameAnalyzer.getResults(landmarks: List<PoseLandmark>){

    for(landmark in landmarks){
        Timber.tag("pliz").i("%s%s", landmark.landmarkType.toString(), landmark.position.toString())
    }

}