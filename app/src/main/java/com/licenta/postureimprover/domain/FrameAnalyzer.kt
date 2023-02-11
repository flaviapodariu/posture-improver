package com.licenta.postureimprover.domain

import android.content.Context
import android.graphics.PointF
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.ui.graphics.Paint
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.licenta.postureimprover.ui.theme.Orange50
import timber.log.Timber
import javax.inject.Inject


class FrameAnalyzer @Inject constructor(): ImageAnalysis.Analyzer {

    private val options = AccuratePoseDetectorOptions.Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()

    lateinit var context: Context

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        var times = 0
        val poseDetector = PoseDetection.getClient(options)
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            poseDetector.process(inputImage)
                .addOnSuccessListener { landmarks ->
                    val bodyLandmarks = landmarks.allPoseLandmarks
                    // if person was detected list is not empty
                    if (bodyLandmarks.isNotEmpty()) {
                        drawResults(landmarksToPositions(bodyLandmarks), inputImage)
                        Timber.tag("landmarkType").d(landmarksToString(bodyLandmarks))
                    } else {
                        times ++
                        Toast.makeText(
                            context,
                            "Person is not inside capture screen!",
                            Toast.LENGTH_SHORT
                        ).show()
                        Timber.tag("times").d(times.toString())

                    }
                }
                    .addOnFailureListener { error ->
                        Timber.tag("Detection").d("${error.stackTrace}")
                    }
                .also { imageProxy.close() }  // avoid blocking the thread or the camera preview

        }
    }

    private fun FrameAnalyzer.landmarksToString(landmarks: List<PoseLandmark>): String {
        var ans = ""
        for (l in landmarks) {
            ans += l.landmarkType.toString() + "-> (" + l.position.x.toString() + ", " + l.position.y.toString() + "); "
        }
        return ans
    }

    private fun FrameAnalyzer.landmarksToPositions(pose: List<PoseLandmark>): Map<String, PointF> {
        return mapOf(
            "leftShoulder" to pose[PoseLandmark.LEFT_SHOULDER].position,
            "rightShoulder" to pose[PoseLandmark.RIGHT_SHOULDER].position,
            "leftElbow" to pose[PoseLandmark.LEFT_ELBOW].position,
            "rightElbow" to pose[PoseLandmark.RIGHT_ELBOW].position,
            "leftWrist" to pose[PoseLandmark.LEFT_WRIST].position,
            "rightWrist" to pose[PoseLandmark.RIGHT_WRIST].position,
            "leftHip" to pose[PoseLandmark.LEFT_HIP].position,
            "rightHip" to pose[PoseLandmark.RIGHT_HIP].position,
            "leftKnee" to pose[PoseLandmark.LEFT_KNEE].position,
            "rightKnee" to pose[PoseLandmark.RIGHT_KNEE].position,
            "leftAnkle" to pose[PoseLandmark.LEFT_ANKLE].position,
            "rightAnkle" to pose[PoseLandmark.RIGHT_ANKLE].position,
            "leftHeel" to pose[PoseLandmark.LEFT_HEEL].position,
            "rightHeel" to pose[PoseLandmark.RIGHT_HEEL].position,
            "leftFootIndex" to pose[PoseLandmark.LEFT_FOOT_INDEX].position,
            "rightFootIndex" to pose[PoseLandmark.RIGHT_FOOT_INDEX].position,
            "nose" to pose[PoseLandmark.NOSE].position,
            "leftEar" to pose[PoseLandmark.LEFT_EAR].position,
            "rightEar" to pose[PoseLandmark.RIGHT_EAR].position
        )

    }

    fun FrameAnalyzer.drawResults(
        map: Map<String, PointF>,
        image: InputImage
    ) {

        val paint = Paint().apply {
            color = Orange50
            strokeWidth = 5.0f
        }

    }

}


