package com.licenta.postureimprover.ui.styles

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import com.licenta.postureimprover.ui.theme.Green80

fun authChangeOptionButtonStyle(): TextStyle {
    return TextStyle(
        color= Green80,
        fontSize = 4.em,
        fontWeight = FontWeight(weight = 600)
    )
}
