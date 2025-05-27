package com.example.assignmentreal.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignmentreal.ui.theme.AssignmentRealTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(name = "GreetingPreview", showBackground = true)
@Composable
fun GreetingPreview() {
    AssignmentRealTheme {
        Greeting("Android")
    }
}

