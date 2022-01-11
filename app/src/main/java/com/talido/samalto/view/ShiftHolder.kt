package com.talido.samalto.view

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Shift
import com.talido.samalto.model.data.toLocalTimeString
import java.util.*

class ShiftHolder( val adapter: ShiftAdapter, itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var selectedStartTime: Calendar
    lateinit var selectedEndTime: Calendar
    val startTimeInput: DateTimeEditText = itemView.findViewById(R.id.startTime)
    val endTimeInput: DateTimeEditText = itemView.findViewById(R.id.endTime)
    val addShift: ImageView = itemView.findViewById(R.id.addShift)

    fun bindShift(position: Int) {
        val shift = adapter.shifts[position]
        startTimeInput.setText(shift.startTime.toLocalTimeString())
        endTimeInput.setText(shift.endTime.toLocalTimeString())
        addShift.visibility = View.GONE

        startTimeInput.setOnDateTimeSetListener { _, dateTime -> adapter.shifts[position].startTime = dateTime}
        endTimeInput.setOnDateTimeSetListener { _, dateTime -> adapter.shifts[position].endTime = dateTime}
    }

    fun bindAddShift() {
        // Initial view properties
        selectedStartTime = if (adapter.shifts.isNotEmpty()) adapter.shifts.last().endTime else adapter.startTime
        selectedEndTime = (selectedStartTime.clone() as Calendar).apply { this.add(Calendar.HOUR_OF_DAY, 2) }
        startTimeInput.setText(selectedStartTime.toLocalTimeString())
        endTimeInput.setText(selectedEndTime.toLocalTimeString())
        addShift.visibility = View.VISIBLE

        // Listeners
        startTimeInput.setOnDateTimeSetListener { _, datetime -> selectedStartTime = datetime }
        endTimeInput.setOnDateTimeSetListener { _, datetime -> selectedEndTime = datetime }
        addShift.setOnClickListener {
            val newShift = Shift(adapter.postName, selectedStartTime, selectedEndTime)
            adapter.shifts.add(newShift)
            adapter.notifyItemChanged(adapter.shifts.size - 1) // Update last item
            adapter.notifyItemInserted(adapter.shifts.size) // Add a new empty item
        }
    }
}