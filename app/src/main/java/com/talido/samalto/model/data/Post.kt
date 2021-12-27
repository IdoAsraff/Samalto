package com.talido.samalto.model.data

import java.util.*

class Post(val name: String) {
    val shifts: MutableList<Shift> = mutableListOf()

    fun addShift(startTime: Calendar, endTime: Calendar) {
        shifts.add(Shift(this, startTime, endTime))
    }
}