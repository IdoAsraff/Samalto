package com.talido.samalto.model.data

import java.util.*

data class Shift(var postName: String, var startTime: Calendar, var endTime: Calendar) {
    override fun toString(): String {
        return "Shift(postName='$postName', startTime=${startTime.toLocalTimeString()}, endTime=${endTime.toLocalTimeString()})"
    }
}

fun Calendar.toLocalTimeString() = "${this[Calendar.HOUR_OF_DAY]}:${this[Calendar.MINUTE].toString().padStart(2, '0')}"