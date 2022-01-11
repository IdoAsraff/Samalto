package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post
import com.talido.samalto.model.data.Shift
import java.util.*

class ShiftAdapter(val context: Context, val startTime: Calendar, val postName: String, val shifts: MutableList<Shift>) :
    RecyclerView.Adapter<ShiftHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShiftHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shift_card, parent, false)
        return ShiftHolder(this, view)
    }

    override fun onBindViewHolder(holder: ShiftHolder, position: Int) {
        if (position == shifts.size)
            holder.bindAddShift()
        else
            holder.bindShift(position)
    }

    override fun getItemCount(): Int {
        return shifts.size + 1
    }
}
