package com.licenta.postureimprover.util

import android.graphics.Typeface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.licenta.postureimprover.theme.Purple40
import com.patrykandpatrick.vico.compose.component.overlayingComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.cornered.Corner
import com.patrykandpatrick.vico.core.component.shape.cornered.MarkerCorneredShape
import com.patrykandpatrick.vico.core.extension.copyColor
import com.patrykandpatrick.vico.core.marker.Marker

@Composable
fun rememberMarker(): Marker {
    val labelBackgroundColor = Color.White
    val labelBackground = remember(labelBackgroundColor) {
        ShapeComponent(labelBackgroundShape, android.graphics.Color.WHITE).setShadow(
            radius = LABEL_BACKGROUND_SHADOW_RADIUS,
            dy = LABEL_BACKGROUND_SHADOW_DY,
            applyElevationOverlay = true,
        )
    }
    val label = textComponent(
        background = labelBackground,
        lineCount = 1,
        padding = labelPadding,
        typeface = Typeface.MONOSPACE,
    )
    val indicatorInnerComponent =
        shapeComponent(Shapes.pillShape, Purple40)
    val indicatorCenterComponent = shapeComponent(Shapes.pillShape, Color.White)
    val indicatorOuterComponent = shapeComponent(Shapes.pillShape, Color.White)
    val indicator = overlayingComponent(
        outer = indicatorOuterComponent,
        inner = overlayingComponent(
            outer = indicatorCenterComponent,
            inner = shapeComponent(Shapes.pillShape, Color.White),
            innerPaddingAll = 5.dp,
        ),
        innerPaddingAll = 10.dp,
    )
    val guideline = null

    return remember(label, indicator, guideline) {
        object : MarkerComponent(label, indicator, guideline) {
            init {
                indicatorSizeDp = 30f
                onApplyEntryColor = { entryColor ->
                    indicatorOuterComponent.color = entryColor.copyColor(alpha = 32)
                    with(indicatorCenterComponent) {
                        color = entryColor
                        setShadow(radius = 12f, color = entryColor)
                    }
                }
            }
        }
    }

}

private const val LABEL_BACKGROUND_SHADOW_RADIUS = 4f
private const val LABEL_BACKGROUND_SHADOW_DY = 2f
private val labelBackgroundShape = MarkerCorneredShape(Corner.FullyRounded)
private val labelHorizontalPaddingValue = 8.dp
private val labelVerticalPaddingValue = 4.dp
private val labelPadding = dimensionsOf(labelHorizontalPaddingValue, labelVerticalPaddingValue)