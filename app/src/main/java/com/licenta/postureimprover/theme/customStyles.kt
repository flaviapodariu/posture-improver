package com.licenta.postureimprover.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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