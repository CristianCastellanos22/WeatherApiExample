package com.example.cristian.weatherapiexample.ui.theme.presentation.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.cristian.weatherapiexample.R

@Composable
fun ErrorDialog(
    messageId: String,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = stringResource(id = R.string.error_dialog_title))
        },
        text = {
            Text(messageId)
        }, confirmButton = {
            Button(
                onClick = { onDialogDismiss() },
            ) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    )
}