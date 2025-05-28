package com.example.assignmentreal.model

import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Request
import okhttp3.Response
import okhttp3.Call
import okhttp3.Callback
import okio.IOException
import kotlin.collections.List

data class Slot(val start: String, val end: String, val tag: String? = null)

class Schedule {
    var available: List<Slot> = listOf()
    var booked: List<Slot> = listOf()
}


fun fetchScheduale(
    url: String,
    onResult: (List<Slot>?) -> Unit
){
    val client = OkHttpClient()
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(Schedule::class.java)

    val request = Request.Builder().url(url).build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            onResult(null)
        }

        override fun onResponse(call: Call, response: Response) {

            val json = response.body?.string()
            val _schedule = json?.let {
                adapter.fromJson(it)
            }
            var schedule: MutableList<Slot> = mutableListOf()
            _schedule?.available?.let{ slots ->
                val updatedSlots = slots.map { it.copy(tag = "available") }
                schedule.addAll(updatedSlots)
            }
            _schedule?.booked?.let{ slots ->
                val updatedSlots = slots.map { it.copy(tag = "booked") }
                schedule.addAll(updatedSlots)
            }

            Log.d("Schedule", "Fetched: $schedule")
            onResult(schedule)
        }
    })
}