package com.talido.samalto.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.R
import com.talido.samalto.databinding.FragmentScheduleStartBinding
import com.talido.samalto.view.ScheduleActivity
import java.util.*

class ScheduleStartFragment : Fragment(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var selectedStart: Calendar
    lateinit var binding: FragmentScheduleStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleStartBinding.inflate(layoutInflater)

        if (!this::selectedStart.isInitialized) {
            selectedStart = Calendar.getInstance()
        } else {
            binding.next.isEnabled = true
            binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
        }

        binding.schedStartInput.setOnClickListener {
            val now = Calendar.getInstance()
            DatePickerDialog(
                requireContext(),
                this,
                now[Calendar.YEAR],
                now[Calendar.MONTH],
                now[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.prev.setOnClickListener {
            requireActivity().finish()
        }
        binding.next.setOnClickListener {
            (activity as ScheduleActivity).startTime = Optional.of(selectedStart)
            findNavController().navigate(ScheduleStartFragmentDirections.actionScheduleStartFragmentToCreatePostsFragment())
        }

        return binding.root
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
            requireContext(),
            this,
            now[Calendar.HOUR_OF_DAY],
            now[Calendar.MINUTE],
            true
        ).show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        selectedStart[Calendar.HOUR_OF_DAY] = hour
        selectedStart[Calendar.MINUTE] = minute
        binding.schedStartInput.setText("$hour:${minute.toString().padStart(2, '0')}")
        binding.next.isEnabled = true
        binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
    }
}