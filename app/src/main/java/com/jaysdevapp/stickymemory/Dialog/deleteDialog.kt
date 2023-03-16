package com.jaysdevapp.stickymemory

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun DeleteDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    onConfirm: () -> Unit
) {
    if (!showDialog) return
    AlertDialog(
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = { setShowDialog(false) },
        title = { Text(text = stringResource(R.string.delete_dialog_title)) },
        text = { Text(text = stringResource(R.string.delete_dialog_text)) },
        confirmButton = {
            Button(
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = {
                    onConfirm()
                    setShowDialog(false)
                }) {
                Text(
                    text = stringResource(R.string.dialog_confirm),
                    color = MaterialTheme.colors.primary
                )
            }
        },
        dismissButton = {
            Button(
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                onClick = { setShowDialog(false) }) {
                Text(
                    text = stringResource(R.string.dialog_cancel),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    )
}