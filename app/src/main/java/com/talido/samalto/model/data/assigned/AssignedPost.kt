package com.talido.samalto.model.data.assigned

import java.util.*

class AssignedPost(val name: String) {
    val shifts: MutableList<AssignedShift> = mutableListOf()

    fun addShift(startTime: Calendar, endTime: Calendar, guard: AssignedGuard) {
        val shiftBefore = guard.shifts.first { startTime.after(it.startTime) }
        if (startTime.before(shiftBefore.endTime)) {
            throw ShiftAdditionException()
        }

        val assignedShift = AssignedShift(this.name, startTime, endTime, guard.name)
        shifts.add(assignedShift)

        guard.shifts.add(guard.shifts.indexOf(shiftBefore) + 1, assignedShift)
    }
}