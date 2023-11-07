package com.br.streamcontrol.ui.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun ConfirmDialog(
    dialogState: MutableState<Boolean> = mutableStateOf(false),
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    title: String = "Atenção!",
    message: String,
    confirmButtonText: String = "Confirmar",
    cancelButtonText: String = "Cancelar"
) {
    if (dialogState.value) {
        AlertDialog(
            onDismissRequest = {
                dialogState.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(
                    text = message,
                    fontSize = TextUnit(16f, TextUnitType.Sp)
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    dialogState.value = false
                    onConfirm()
                }) {
                    Text(confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogState.value = false
                    onCancel()
                }) {
                    Text(cancelButtonText)
                }
            }
        )
    }
}