package com.example.assignmentreal.share

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.assignmentreal.model.Schedule
import com.example.assignmentreal.model.fetchScheduale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class ShareViewModel: ViewModel() {
    private val _week = MutableStateFlow(
        LocalDate.now().minusDays(
            LocalDate.now().dayOfWeek.value.toLong() % 7
        )
    )
    val week: StateFlow<LocalDate> = _week.asStateFlow()

    private val _weekDay = MutableStateFlow(1)
    val weekDay: StateFlow<Int> = _weekDay.asStateFlow()

    private val _schedule = MutableStateFlow(Schedule())
    val schedule: StateFlow<Schedule> = _schedule.asStateFlow()

    fun selectWeekDay(day: Int){
        _weekDay.value = day
    }
    fun selectWeek(week: LocalDate){
        _week.value = week
    }
    fun loadSchedule(url: String) {
        fetchScheduale(url) { res ->
            if (res != null) {
                _schedule.value = res
            }
        }
    }
}