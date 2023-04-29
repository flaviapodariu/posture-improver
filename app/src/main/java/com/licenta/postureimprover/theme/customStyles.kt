package com.licenta.postureimprover.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

fun authChangeOptionButtonStyle(): TextStyle {
    return TextStyle(
        color= Green80,
        fontSize = 4.em,
        fontWeight = FontWeight(weight = 600)
    )
}

val Icons.Workouts: ImageVector
    get() {
        if (_workouts != null) {
            return _workouts!!
        }
        _workouts = materialIcon(name = "Workouts") {
            materialPath {
                moveTo(5.0F, 12.0F)
                curveToRelative(0.0F, 4.0F, 0.4F, 7.0F, 1.0F, 7.0F)
                curveToRelative(0.6F, -0.0F, 1.0F, -1.4F, 1.0F, -3.0F)
                curveToRelative(0.0F, -2.9F, 0.1F, -3.0F, 5.0F, -3.0F)
                curveToRelative(4.9F, -0.0F, 5.0F, 0.1F, 5.0F, 3.0F)
                curveToRelative(0.0F, 1.6F, 0.5F, 3.0F, 1.0F, 3.0F)
                curveToRelative(0.6F, -0.0F, 1.0F, -3.0F, 1.0F, -7.0F)
                curveToRelative(0.0F, -4.0F, -0.4F, -7.0F, -1.0F, -7.0F)
                curveToRelative(-0.5F, -0.0F, -1.0F, 1.3F, -1.0F, 3.0F)
                curveToRelative(0.0F, 2.9F, -0.1F, 3.0F, -5.0F, 3.0F)
                curveToRelative(-4.9F, -0.0F, -5.0F, -0.1F, -5.0F, -3.0F)
                curveToRelative(0.0F, -1.7F, -0.4F, -3.0F, -1.0F, -3.0F)
                curveToRelative(-0.6F, -0.0F, -1.0F, 3.0F, -1.0F, 7.0F)
                close()
            }
            materialPath {
                moveTo(2.0F, 8.9F)
                curveToRelative(0.0F, 1.1F, -0.6F, 2.2F, -1.2F, 2.5F)
                curveToRelative(-1.0F, 0.5F, -1.0F, 0.7F, 0.0F, 1.2F)
                curveToRelative(0.6F, 0.3F, 1.2F, 1.4F, 1.2F, 2.5F)
                curveToRelative(0.0F, 1.0F, 0.5F, 1.9F, 1.0F, 1.9F)
                curveToRelative(0.6F, -0.0F, 1.0F, -2.3F, 1.0F, -5.0F)
                curveToRelative(0.0F, -2.8F, -0.4F, -5.0F, -1.0F, -5.0F)
                curveToRelative(-0.5F, -0.0F, -1.0F, 0.9F, -1.0F, 1.9F)
                close()
            }
            materialPath {
                moveTo(20.0F, 12.0F)
                curveToRelative(0.0F, 2.7F, 0.5F, 5.0F, 1.0F, 5.0F)
                curveToRelative(0.6F, -0.0F, 1.0F, -0.9F, 1.0F, -1.9F)
                curveToRelative(0.0F, -1.1F, 0.6F, -2.2F, 1.3F, -2.5F)
                curveToRelative(1.0F, -0.5F, 1.0F, -0.7F, 0.0F, -1.2F)
                curveToRelative(-0.7F, -0.3F, -1.3F, -1.4F, -1.3F, -2.5F)
                curveToRelative(0.0F, -1.0F, -0.4F, -1.9F, -1.0F, -1.9F)
                curveToRelative(-0.5F, -0.0F, -1.0F, 2.2F, -1.0F, 5.0F)
                close()
            }
        }
        return _workouts!!
    }

private var _workouts: ImageVector? = null

val Icons.Camera: ImageVector
    get() {
        if (_camera != null) {
            return _camera!!
        }
        _camera = materialIcon(name = "Camera") {
            path(
                fill = SolidColor(Color(0xFFFFFFFF)),
                fillAlpha = 1.0F,
                strokeAlpha = 1.0F,
                strokeLineWidth = 0.0F,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 4.0F,
                pathFillType = PathFillType.NonZero,
            ) {
                moveTo(3.0F, 4.0F)
                verticalLineTo(1.0F)
                horizontalLineToRelative(2.0F)
                verticalLineToRelative(3.0F)
                horizontalLineToRelative(3.0F)
                verticalLineToRelative(2.0F)
                horizontalLineTo(5.0F)
                verticalLineToRelative(3.0F)
                horizontalLineTo(3.0F)
                verticalLineTo(6.0F)
                horizontalLineTo(0.0F)
                verticalLineTo(4.0F)
                horizontalLineTo(3.0F)

                moveTo(6.0F, 10.0F)
                verticalLineTo(7.0F)
                horizontalLineToRelative(3.0F)
                verticalLineTo(4.0F)
                horizontalLineToRelative(7.0F)
                lineToRelative(1.83F, 2.0F)
                horizontalLineTo(21.0F)
                curveToRelative(1.1F, 0.0F, 2.0F, 0.9F, 2.0F, 2.0F)
                verticalLineToRelative(12.0F)
                curveToRelative(0.0F, 1.1F, -0.9F, 2.0F, -2.0F, 2.0F)
                horizontalLineTo(5.0F)
                curveToRelative(-1.1F, 0.0F, -2.0F, -0.9F, -2.0F, -2.0F)
                verticalLineTo(10.0F)
                horizontalLineTo(6.0F)

                moveTo(13.0F, 19.0F)
                curveToRelative(2.76F, 0.0F, 5.0F, -2.24F, 5.0F, -5.0F)
                reflectiveCurveToRelative(-2.24F, -5.0F, -5.0F, -5.0F)
                reflectiveCurveToRelative(-5.0F, 2.24F, -5.0F, 5.0F)
                reflectiveCurveTo(10.24F, 19.0F, 13.0F, 19.0F)

                moveTo(9.8F, 14.0F)
                curveToRelative(0.0F, 1.77F, 1.43F, 3.2F, 3.2F, 3.2F)
                reflectiveCurveToRelative(3.2F, -1.43F, 3.2F, -3.2F)
                reflectiveCurveToRelative(-1.43F, -3.2F, -3.2F, -3.2F)
                reflectiveCurveTo(9.8F, 12.23F, 9.8F, 14.0F)
                close()
            }
        }
        return _camera!!
    }

private var _camera: ImageVector? = null

@Composable
fun CameraShutter() {
    Canvas(
        modifier = Modifier
            .size(size = 50.dp)
    ) {
        drawCircle(
            color = Color.White
        )
    }
}