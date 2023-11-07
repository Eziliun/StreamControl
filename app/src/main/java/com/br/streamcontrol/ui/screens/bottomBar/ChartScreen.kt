package com.br.streamcontrol.ui.screens.bottomBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.br.streamcontrol.domain.viewmodel.HomeViewModel

@Composable
fun ChartContent(
    contentPadding: PaddingValues,
    homeViewModel: HomeViewModel
) {
    Text("Charts", maxLines = 1, overflow = TextOverflow.Ellipsis)
}
