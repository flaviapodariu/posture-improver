package com.licenta.postureimprover.domain

import android.graphics.PointF
import com.google.mlkit.vision.pose.PoseLandmark
import timber.log.Timber
import kotlin.math.*

/*
    Helpers for angle computation
    ab -> segment 1
    bc -> segment 2
    b -> middle point

 */
    fun radiansToDegrees(radians: Float): Float {
        return ((radians * 2 * PI) / (2 * 180)).toFloat()
    }

    fun slope(a: PointF, b: PointF): Float {
        return (b.y - a.y)/(b.x - a.x)
    }

    fun distanceBetween(a: PointF, b: PointF) : Float {
        return (b.x - a.x).pow(2) + (b.y - a.y).pow(2)
    }


    fun angle(a: PointF, b: PointF, c:PointF): Float {
        val ab = distanceBetween(a, b)
        val bc = distanceBetween(b, c)
        val ac = distanceBetween(a, c)
        var cosinus = (ab + ac - bc) / 2 * sqrt(ab) * sqrt(ac)
        cosinus = (cosinus % (2 * PI)).toFloat()
        Timber.tag("cos").d("cos $cosinus")
        var degrees = abs(radiansToDegrees(acos(cosinus)))
        if(degrees > 180) {
            degrees = 360 - degrees
        }
        return degrees
    }

/**
 *   Helpers for type of posture problem. Each type has a value:
 *    lordosis = 1
 *    head forward = 2
 *
 *
 */
    fun lordosis(a: PointF, b:PointF, c:PointF): Boolean {
        val an = angle(c, b, a)
        Timber.tag("lordosis").i(an.toString())
        return true

//        return false
    }

    fun headForward(torso: PointF, shoulder:PointF, nose:PointF): Boolean {
        val degrees = angle(torso, shoulder, nose)
        Timber.tag("head").d(degrees.toString())
        if (degrees > 119) {

            return true
        }

        return false
    }

/**
 *  @return the sum of postural problems
 */
    fun checkPosture(body: List<PoseLandmark>): Int {
        var problems = 0

//        if(lordosis(body[12].position, body[24].position, body[26].position))
//            problems += 1

        if(headForward(body[12].position, body[24].position, body[0].position) or
                headForward(body[11].position, body[23].position, body[0].position)
        ){
            problems += 2
        }

        return problems

    }


