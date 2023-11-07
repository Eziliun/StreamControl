package com.br.streamcontrol.data.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.br.streamcontrol.domain.routes.Screen

data class NavigationItem(
    val title: String,
    val description: String,
    val itemId: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Screen
)