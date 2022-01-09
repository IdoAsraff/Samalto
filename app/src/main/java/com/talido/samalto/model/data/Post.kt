package com.talido.samalto.model.data

import java.util.*

open class Post {
    var sufferingLevel: Int
    var shifts: MutableList<Shift> = mutableListOf()
    var name: String set(value) {
        shifts.forEach { it.postName = value }
        field = value
    }

    constructor(
        name: String = "",
        sufferingLevel: Int = 0,
        shifts: MutableList<Shift> = mutableListOf()
    ) {
        this.name = name
        this.sufferingLevel = sufferingLevel
        this.shifts = shifts
    }

    fun addShift(startTime: Calendar, endTime: Calendar) {
        shifts.add(Shift(this.name, startTime, endTime))
    }

    override fun toString(): String {
        return "Post(name='$name', sufferingLevel=$sufferingLevel, shifts=$shifts)"
    }
}