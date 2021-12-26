package com.talido.samalto.model.data

import java.time.LocalTime

class Post(val name: String) {
    val shifts: MutableList<Shift> = mutableListOf()

    fun addShift(startTime: LocalTime, endTime: LocalTime) {
        shifts.add(Shift(this, startTime, endTime))
    }
}