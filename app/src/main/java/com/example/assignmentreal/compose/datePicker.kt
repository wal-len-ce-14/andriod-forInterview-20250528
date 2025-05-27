package com.example.assignmentreal.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmentreal.ui.theme.AssignmentRealTheme
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun datePicker(){

    var weekLater  by remember { mutableStateOf(0) }
    var weekDay by remember { mutableStateOf(1)}

    val today = LocalDate.now()
    val dayOfWeek = today.dayOfWeek.value
    val baseSunday = today.minusDays(dayOfWeek.toLong() % 7)
    val (sunday, saturday) = getNowWeek(baseSunday, weekLater)

    Column {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = { if(weekLater-1>=0) weekLater -= 1; else weekLater  = 0  }) {
                Text("<")
            }
            Text(text = "$sunday~$saturday")
            Button(
                modifier = Modifier.padding(4.dp),
                onClick = { weekLater    += 1 }
            ) {
                Text(">")
            }
        }
        Row(modifier = Modifier
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,

        ){
            Button(
                onClick = { weekDay = 1 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 1) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Sunday")
            }
            Button(
                onClick = { weekDay = 2 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 2) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Monday")
            }
            Button(
                onClick = { weekDay = 3 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 3) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Tuesday")
            }
            Button(
                onClick = { weekDay = 4 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 4) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Wednesday")
            }
            Button(
                onClick = { weekDay = 5 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 5) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Thursday")
            }
            Button(
                onClick = { weekDay = 6 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 6) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Friday")
            }
            Button(
                onClick = { weekDay = 7 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (weekDay == 7) Color(0xFF1976D2) else Color.LightGray,
                    contentColor = Color.White
                )
            ){
                Text(text = "Saturday")
            }

        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "datePicker", showBackground = true)
@Composable
fun datePickerPreview() {
    AssignmentRealTheme {
        datePicker()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getNowWeek(baseSunday: LocalDate, weekLater: Number): Pair<LocalDate, LocalDate>{
    val selectSunday = baseSunday.plusWeeks(weekLater.toLong())
    val selectSaturday = selectSunday.plusDays(6)
    return Pair<LocalDate, LocalDate>(selectSunday,selectSaturday)
}