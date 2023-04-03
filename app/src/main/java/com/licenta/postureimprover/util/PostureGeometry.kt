package com.licenta.postureimprover.util

import android.graphics.PointF
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
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

/**
 *  Reason for minus when computing angle:
 *      segment AB (torso-shoulder) will always be under x+ axis, meaning
 *      that the angle between x+ and AB would be clockwise => negative
 *
 *      the coordinates are given as positives, but the y values are practically negatives
 *      because all the body landmarks are inside the 4h quadrant
 */

    fun angle(a: PointF, b: PointF, c:PointF): Float {
      //translate points by -b (b in the vertex of the angle)
        val atanC = atan2(-c.y + b.y, c.x - b.x)
        val atanA = atan2(-a.y + b.y, a.x - b.x)
//        Timber.tag("atans").d("${radiansToDegrees(atanC)}, ${radiansToDegrees(atanA)}")
        return radiansToDegrees( atanC - atanA)
    }

/**
 *   Helpers for type of posture problem. Each type has a value:
 *    lordosis = 1
 *    head forward = 2
 *
 *
 */
    fun lordosis(shoulders: PointF, torso: PointF, knees: PointF): Float {
        val angle = angle(shoulders, torso, knees)
//        Timber.tag("lordosis").i("$angle")
        return angle
    }

    fun headForward(torso: PointF, shoulders:PointF, nose:PointF): Float {
        var degrees = angle(torso, shoulders, nose)
        // if points belong to the 2nd and 3rd quadrants, we get the outer angle
        if(degrees > 180){
            degrees = 360 - degrees
        }
//        Timber.tag("head").d("$degrees")
        return degrees
    }


    //maybe compute estimated height loss related
    fun roundedShoulders(ears: PointF, shoulders: PointF) : Float {
        return abs(ears.x - shoulders.x)
    }

    fun mean(left: PointF, right: PointF) : PointF {
        return PointF(
            (left.x + right.x) / 2,
            (left.y + right.y) / 2
        )
    }

fun checkPosture(body: List<PoseLandmark>): CaptureReq {
    val nose = body[0].position
    val ears = mean(body[7].position, body[8].position)
    val shoulders = mean(body[11].position, body[12].position)
    val torso = mean(body[23].position, body[24].position)
    val knees = mean(body[25].position, body[26].position)


    return CaptureReq(
        headForward = headForward(torso, shoulders, nose),
        lordosis = lordosis(shoulders, torso, knees),
        roundedShoulders = roundedShoulders(ears, shoulders)
    )

}


