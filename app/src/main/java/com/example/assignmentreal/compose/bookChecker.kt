package com.example.assignmentreal.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignmentreal.model.fetchScheduale
import com.example.assignmentreal.share.ShareViewModel
import com.example.assignmentreal.ui.theme.AssignmentRealTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun bookChecker(viewModel: ShareViewModel = viewModel()){
    val weekDay by viewModel.weekDay.collectAsState()
    val week by viewModel.week.collectAsState()
    val schedule by viewModel.schedule.collectAsState()
    schedule.sortedBy { it.start }
    LaunchedEffect(Unit) {
        viewModel.loadSchedule(
            "https://en.amazingtalker.com/v1/guest/teachers/jamie-coleman/schedule?started_at=${weekDay}")
    }
    if (schedule.isNotEmpty()){
        LazyColumn (
            modifier = Modifier
                .height(400.dp)
        ) {
            items(schedule){ slot ->
                val slotDay = LocalDate.parse(slot.start.substring(0, 10))
                if(slotDay == week.plusDays((weekDay-1).toLong())){
                    val bgColor = if (slot.tag == "available") Color(0xFFA8E6A3) else Color(0xFFB7C6AA)
                    Text(
                        modifier = Modifier
                            .background(color = bgColor, shape = RoundedCornerShape(12.dp))
                            .padding(8.dp),
                        text = slot.start.substring(11,16)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
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