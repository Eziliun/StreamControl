package com.br.streamcontrol.ui.permissions

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.br.streamcontrol.domain.viewmodel.LocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestPermission(viewModel: LocationViewModel) {
    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                acc && isPermissionGranted
            }

            if (!permissionsGranted) {
                // Logic when the permissions were not granted by the user
                // You can show a message to the user or perform some action.
            } else {
                viewModel.requestLocation()
            }
        }
    )

    // Request location permissions when this composable is displayed.
    DisposableEffect(Unit) {
        locationPermissionLauncher.launch(locationPermissions)
        onDispose { /* Cleanup, if needed */ }
    }
}