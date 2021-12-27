package com.talido.samalto.model.data

import java.util.*

class Post(var name: String = "", var sufferingLevel: Int=0) {
    val shifts: MutableList<Shift> = mutableListOf()

    fun addShift(startTime: Calendar, endTime: Calendar) {
        shifts.add(Shift(this.name, startTime, endTime))
    }
}