package com.talido.samalto.model.data.assigned

import java.util.*

class AssignedPost(val name: String) {
    val shifts: MutableList<AssignedShift> = mutableListOf()

    fun addShift(newStartTime: Calendar, newEndTime: Calendar, guard: AssignedGuard) {
        val assignedShift = AssignedShift(this.name, newStartTime, newEndTime, guard.name)

        if (guard.shifts.isNotEmpty() && guard.shifts.any { newStartTime.after(it.startTime) }) {
            val shiftBefore = guard.shifts.first { newStartTime.after(it.startTime) }
            if (newStartTime.before(shiftBefore.endTime)) {
                throw ShiftAdditionException()
            }
            guard.shifts.add(guard.shifts.indexOf(shiftBefore) + 1, assignedShift)
        } else {
            guard.shifts.add(assignedShift)
        }
        shifts.add(assignedShift)
    }
}