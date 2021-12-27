package com.talido.samalto.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.talido.samalto.databinding.ActivityScheduleStartBinding
import java.util.*

class ScheduleStartActivity : AppCompatActivity() {
    val selectedStart: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityScheduleStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val dateTimePicker = object : DialogFragment(), DatePickerDialog.OnDateSetListener,
            TimePickerDialog.OnTimeSetListener {
            override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                val now = Calendar.getInstance()
                return DatePickerDialog(
                    this@ScheduleStartActivity,
                    this,
                    now[Calendar.YEAR],
                    now[Calendar.MONTH],
                    now[Calendar.DAY_OF_MONTH])
            }

            override fun onDateSet(view: DatePicker?, selectedYear: Int, selectedMonth: Int, selectedDay: Int) {
                selectedStart[Calendar.YEAR] = selectedYear
                selectedStart[Calendar.MONTH] = selectedMonth
                selectedStart[Calendar.DAY_OF_MONTH] = selectedDay
                object : DialogFragment() {

                }
            }

            override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
                selectedStart[Calendar.HOUR_OF_DAY] = hour
                selectedStart[Calendar.MINUTE] = minute
            }
        }
        binding.schedStartInput.setOnClickListener {
            val now = Calendar.getInstance()
        }

        binding.next.setOnClickListener {
            val (selectedHour, selectedMinute) = binding.schedStartInput.text.split(":")
                .map { it.toInt() }
            val intent = Intent(this, CreatePostsActivity::class.java).apply {
                putExtra("schedStartTime", 0)
            }
            startActivity(intent)
        }
    }

}