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
    lateinit var inputStartTime: Calendar
    lateinit var inputEndTime: Calendar
    val startTimeInput: EditText = itemView.findViewById(R.id.startTime)
    val endTimeInput: EditText = itemView.findViewById(R.id.endTime)
    val addShift: ImageView = itemView.findViewById(R.id.addShift)

    fun bindShift(shift: Shift) {
        startTimeInput.setText(shift.startTime.toLocalTimeString())
        endTimeInput.setText(shift.endTime.toLocalTimeString())
        addShift.visibility = View.GONE
    }

    fun bindAddShift(adapter: ShiftAdapter) {
        inputStartTime = if (adapter.shifts.isNotEmpty()) adapter.shifts.last().endTime else adapter.startTime
        inputEndTime = (inputStartTime.clone() as Calendar).apply { this.add(Calendar.HOUR_OF_DAY, 2) }
        startTimeInput.setText(inputStartTime.toLocalTimeString())
        endTimeInput.setText(inputEndTime.toLocalTimeString())
        addShift.visibility = View.VISIBLE

        addShift.setOnClickListener {
            val newShift = Shift(adapter.postName, inputStartTime, inputEndTime)
            adapter.shifts.add(newShift)
            adapter.notifyItemChanged(adapter.shifts.size - 1) // Update last item
            adapter.notifyItemInserted(adapter.shifts.size) // Add a new empty item
        }
    }
}