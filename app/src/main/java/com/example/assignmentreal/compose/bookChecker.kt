package com.example.assignmentreal.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignmentreal.model.fetchScheduale
import com.example.assignmentreal.share.ShareViewModel
import com.example.assignmentreal.ui.theme.AssignmentRealTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun bookChecker(viewModel: ShareViewModel = viewModel()){
    val weekDay by viewModel.weekDay.collectAsState()
    val week by viewModel.week.collectAsState()
    val schedule by viewModel.schedule.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSchedule("https://en.amazingtalker.com/v1/guest/teachers/jamie-coleman/schedule?started_at=2025-06-01")
    }
    Column {
        Text(text = "is in book checker")
        Text(text = "week $week, weekday $weekDay")
    }
    if (schedule.available.isNotEmpty()){
        LazyColumn {
            items(schedule.available){ slot ->
                Text(text = "${slot.start} at ${slot.end}")
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "bookChecker", showBackground = true)
@Composable
fun bookCheckerPreview() {
    AssignmentRealTheme {
        bookChecker()
    }
}