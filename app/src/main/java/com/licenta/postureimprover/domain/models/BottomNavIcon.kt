package com.licenta.postureimprover.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavIcon(
    val route: String,
    val icon: ImageVector,
    val description: String
)