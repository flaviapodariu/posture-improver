package com.licenta.postureimprover.util

import android.graphics.PointF
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import timber.log.Timber
import kotlin.math.abs
import kotlin.math.atan2

/*
    Helpers for angle computation
    ab -> segment 1
    bc -> segment 2
    b -> middle point

 */
    fun radiansToDegrees(radians: Float): Float {
        return ((radians * 180) / Math.PI).toFloat()
    }

    const val HEAD_FORWARD_NORMAL = 53f
    const val ROUNDED_SHOULDERS_NORMAL = 52f
/**
 *  Reason for minus when computing angle:
 *      segment AB (torso-shoulder) will always be under x+ axis, meaning
 *      that the angle between x+ and AB would be clockwise => negative
 *
 *      the coordinates are given as positives, but the y values are practically negatives
 *      because all the body landmarks are inside the 4th quadrant
 */

    fun angle(a: PointF, b: PointF, c:PointF): Float {
      //translate points by -b (b in the vertex of the angle)
        val atanC = atan2(c.y - b.y, c.x - b.x)
        val atanA = atan2(a.y - b.y, a.x - b.x)
//        Timber.tag("atans").d("${radiansToDegrees(atanC)}, ${radiansToDegrees(atanA)}")
        return abs(radiansToDegrees( atanC - atanA))
    }

    fun lordosis(ankles: PointF, midPoint: PointF, shoulders: PointF): Float {
        var degrees = angle(ankles, midPoint, shoulders)
        Timber.tag("lordosiss").d("$degrees")
        if(degrees > 180){
            degrees = 360 - degrees
        }
        return degrees
    }

    fun headForward(C7: PointF, ears: PointF): Float {
        val yProjectionFromC7 = PointF(ears.x, C7.y)
        var degrees = angle(yProjectionFromC7, C7, ears)
        // if points belong to the 2nd and 3rd quadrants, we get the outer angle
        if(degrees > 180){
            degrees = 360 - degrees
        }
        Timber.tag("head").d("$degrees")
        return degrees
    }


    fun roundedShoulders(C7: PointF, shoulders: PointF) : Float {
        val yProjectionFromShoulder = PointF(C7.x, shoulders.y)

        var degrees = angle(yProjectionFromShoulder, shoulders, C7)
        if(degrees > 180){
            degrees = 360 - degrees
        }
        Timber.tag("Sh angle").d("$degrees")
        return degrees
    }

    fun mean(left: PointF, right: PointF) : PointF {
        return PointF(
            (left.x + right.x) / 2,
            (left.y + right.y) / 2
        )
    }

    fun checkPosture(body: List<PoseLandmark>): CaptureReq {
        val nose = body[0].position
        val mouth = mean(body[9].position, body[10].position)
        val ears = mean(body[7].position, body[8].position)
        val shoulders = mean(body[11].position, body[12].position)
        val torso = mean(body[23].position, body[24].position)
        val knees = mean(body[25].position, body[26].position)
        val ankles = mean(body[27].position, body[28].position)
        val midpointToShoulders =  PointF(
            ankles.x,
            (ankles.y + shoulders.y) / 2
        )

        val C7 = PointF(
            shoulders.x - (nose.x - ears.x)/2,
            (nose.y + shoulders.y)/2
        )


        return CaptureReq(
            headForward = headForward(C7, ears),
            lordosis = lordosis(ankles, midpointToShoulders, shoulders),
            roundedShoulders = roundedShoulders(C7, shoulders)
        )

    }


