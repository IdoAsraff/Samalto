package com.talido.samalto.view

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Shift
import com.talido.samalto.model.data.toLocalTimeString
import java.util.*

class ShiftHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val startTimeInput: EditText = itemView.findViewById(R.id.startTime)
    val endTimeInput: EditText = itemView.findViewById(R.id.endTime)
    val addShift: ImageView = itemView.findViewById(R.id.addShift)

    fun bindShift(shift: Shift) {
        startTimeInput.setText(shift.startTime.toLocalTimeString())
        endTimeInput.setText(shift.endTime.toLocalTimeString())
        addShift.visibility = View.GONE
    }

    fun bindAddShift(adapter: ShiftAdapter) {
        val initialStartTime = if (adapter.shifts.isNotEmpty()) adapter.shifts.last().endTime else adapter.startTime
        val initialEndTime = (initialStartTime.clone() as Calendar).apply { this.add(Calendar.HOUR_OF_DAY, 2) }
        startTimeInput.setText(initialStartTime.toLocalTimeString())
        endTimeInput.setText(initialEndTime.toLocalTimeString())
        addShift.visibility = View.VISIBLE
    }
}