package com.example.assignmentreal.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {

    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    if (dragAmount < -10) {
                        visible = false
                    }
                    if (dragAmount > 10){
                        visible = true
                    }
                }
            },

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(visible)
            datePicker()
        bookChecker()

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, name = "MainScreen Preview")
@Composable
fun MainScreenPreview() {
    MainScreen()
}