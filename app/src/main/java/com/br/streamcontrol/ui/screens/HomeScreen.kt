package com.br.streamcontrol.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.br.streamcontrol.data.dummy.NavigationItemDummy.navItems
import com.br.streamcontrol.domain.routes.Router
import com.br.streamcontrol.domain.routes.Screen
import com.br.streamcontrol.domain.routes.SystemBackButtonHandler
import com.br.streamcontrol.domain.viewmodel.HomeViewModel
import com.br.streamcontrol.domain.viewmodel.LocationViewModel
import com.br.streamcontrol.ui.permissions.RequestPermission
import com.br.streamcontrol.ui.screens.bottomBar.CardsContent
import com.br.streamcontrol.ui.screens.bottomBar.ChartContent
import com.br.streamcontrol.ui.screens.bottomBar.ProfileContent
import com.br.streamcontrol.ui.screens.topBar.TopAppBarForCards
import com.br.streamcontrol.ui.screens.topBar.TopAppBarForChart
import com.br.streamcontrol.ui.screens.topBar.TopAppBarForHome
import com.br.streamcontrol.ui.screens.topBar.TopAppBarForProfile
import com.br.streamcontrol.ui.widgets.ConfirmDialog

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val selectedItemState = remember { mutableIntStateOf(0) }
    val locationViewModel: LocationViewModel = viewModel()
    RequestPermission(locationViewModel)
    homeViewModel.getUserData()
    homeViewModel.getAllUsers()

    Scaffold(
        topBar = {
            when (selectedItemState.intValue) {
                0 -> TopAppBarForHome()
                1 -> TopAppBarForCards()
                2 -> TopAppBarForChart()
                3 -> TopAppBarForProfile()
                else -> TopAppBarForHome()
            }
        },
        content = { contentPadding ->
            when (selectedItemState.intValue) {
                0 -> HomeContent(contentPadding, homeViewModel)
                1 -> CardsContent(contentPadding, homeViewModel)
                2 -> ChartContent(contentPadding, homeViewModel)
                3 -> ProfileContent(contentPadding, homeViewModel)
                else -> HomeContent(contentPadding, homeViewModel)
            }
        },
        bottomBar = {
            BottomNavigation(selectedItemState)
        },
    )

    SystemBackButtonHandler {
        openDialog.value = true
    }

    ConfirmDialog(
        dialogState = openDialog,
        message = "Tem certeza que deseja sair?",
        onConfirm = {
            homeViewModel.logout()
            Router.navigateTo(Screen.LoginScreen)
        },
    )
}

@Composable
private fun HomeContent(
    contentPadding: PaddingValues,
    homeViewModel: HomeViewModel
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = CircleShape,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            if (homeViewModel.photo != null) {
                AsyncImage(
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.FillWidth,
                    model = homeViewModel.photo,
                    contentDescription = null,
                    alignment = Alignment.Center
                )
            } else {
                Box(
                    modifier = Modifier.size(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        Column {
            Text(
                text = "Seja bem vindo,",
                fontSize = TextUnit(16F, TextUnitType.Sp),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "${homeViewModel.emailId.value}",
                fontSize = TextUnit(20F, TextUnitType.Sp),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun BottomNavigation(selectedItemState: MutableState<Int>) {
    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.selectedIcon, contentDescription = item.description) },
                selected = selectedItemState.value == index,
                onClick = {
                    selectedItemState.value = index
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}