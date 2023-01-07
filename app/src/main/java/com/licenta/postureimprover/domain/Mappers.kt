package com.licenta.postureimprover.domain

import android.graphics.PointF
import androidx.compose.ui.geometry.Offset

fun PointToOffset(point: PointF): Offset{
    return Offset(point.x, point.y)
}