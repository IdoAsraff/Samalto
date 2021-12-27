package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Shift
import java.util.*

class ShiftAdapter(val context: Context, val shifts: MutableList<Shift>) :
    RecyclerView.Adapter<ShiftHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shift_card, parent, false)
        return ShiftHolder(view)
    }

    override fun onBindViewHolder(holder: ShiftHolder, position: Int) {
        with (shifts[position]) {
            holder.startTime.setText("${startTime[Calendar.HOUR_OF_DAY]}:${startTime[Calendar.MINUTE]}")
            holder.endTime.setText("${endTime[Calendar.HOUR_OF_DAY]}:${endTime[Calendar.MINUTE]}")
        }
    }

    override fun getItemCount(): Int {
        return shifts.size
    }
}
class ShiftHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val startTime: EditText = itemView.findViewById(R.id.startTime)
    val endTime: EditText = itemView.findViewById(R.id.endTime)
}
