package com.br.streamcontrol.data.dummy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import com.br.streamcontrol.data.model.NavigationItem
import com.br.streamcontrol.domain.routes.Screen

object NavigationItemDummy {
    val navItems = listOf(
        NavigationItem(
            "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            description = "Home",
            itemId = "1",
            route = Screen.HomeScreen

        ),
        NavigationItem(
            "Cartões",
            selectedIcon = Icons.Filled.CreditCard,
            unselectedIcon = Icons.Outlined.CreditCard,
            description = "Cartões",
            itemId = "3",
            route = Screen.SettingsScreen

        ),
        NavigationItem(
            "Gráficos",
            selectedIcon = Icons.Filled.BarChart,
            unselectedIcon = Icons.Outlined.BarChart,
            description = "Gráficos",
            itemId = "3",
            route = Screen.SettingsScreen

        ),
        NavigationItem(
            "Perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            description = "Perfil",
            itemId = "2",
            route = Screen.ProfileScreen

        ),
    )
}