package com.talido.samalto.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.talido.samalto.R
import com.talido.samalto.databinding.ActivityScheduleStartBinding
import java.util.*

class ScheduleStartActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    private val selectedStart: Calendar = Calendar.getInstance()
    lateinit var binding: ActivityScheduleStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.schedStartInput.setOnClickListener {
            val now = Calendar.getInstance()
            DatePickerDialog(
                this,
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.prev.setOnClickListener {
            finish()
        }
        binding.next.setOnClickListener {
            val intent = Intent(this, CreatePostsActivity::class.java).apply {
                putExtra("scheduleStartCalendar", selectedStart)
            }
            startActivity(intent)
        }
    }

    override fun onDateSet(
        view: DatePicker?,
        selectedYear: Int,
        selectedMonth: Int,
        selectedDay: Int
    ) {
        val now = Calendar.getInstance()
        selectedStart[Calendar.YEAR] = selectedYear
        selectedStart[Calendar.MONTH] = selectedMonth
        selectedStart[Calendar.DAY_OF_MONTH] = selectedDay
        TimePickerDialog(
            this@ScheduleStartActivity,
            this,
            now[Calendar.HOUR_OF_DAY],
            now[Calendar.MINUTE],
            true
        ).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        selectedStart[Calendar.HOUR_OF_DAY] = hour
        selectedStart[Calendar.MINUTE] = minute
        binding.schedStartInput.setText("$hour:$minute")
        binding.next.isEnabled = true
        binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
        binding.next.setTypeface(null, Typeface.BOLD)
    }
}