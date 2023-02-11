package com.licenta.postureimprover.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun SettingsItem(
    description: String,
    onClick: () -> Unit

) {
    Icon(
        imageVector = Icons.Rounded.ExitToApp,
        contentDescription = description
    )
}