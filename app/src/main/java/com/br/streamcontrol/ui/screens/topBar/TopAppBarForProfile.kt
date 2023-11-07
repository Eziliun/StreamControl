package com.br.streamcontrol.ui.screens.topBar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarForProfile() {
    CenterAlignedTopAppBar(
        title = {
            Text("Perfil", maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
    )
}