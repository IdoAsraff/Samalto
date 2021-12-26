package com.talido.samalto.model.data.assigned

import java.time.LocalTime

class AssignedPost(val name: String) {
    val shifts: MutableList<AssignedShift> = mutableListOf()

    fun addShift(startTime: LocalTime, endTime: LocalTime, guard: AssignedGuard) {
        val shiftBefore = guard.shifts.first { it.startTime.compareTo(startTime) > 0 }
        if (shiftBefore.endTime.compareTo(startTime) < 0) {
            throw ShiftAdditionException()
        }

        val assignedShift = AssignedShift(this, startTime, endTime, guard)
        shifts.add(assignedShift)

        guard.shifts.add(guard.shifts.indexOf(shiftBefore) + 1, assignedShift)
    }
}