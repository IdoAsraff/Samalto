package com.talido.samalto.model.data.assigned

import com.talido.samalto.model.data.Guard
import com.talido.samalto.model.data.Post
import com.talido.samalto.model.data.Shift

class Schedule(posts: List<Post>, guards: List<Guard>) {
    val posts: List<AssignedPost> = posts.map { AssignedPost(it.name) }
    val guards: List<AssignedGuard> = guards.map { AssignedGuard(it.name) }

    fun assign(guard: Guard, shift: Shift) {
        val assignedGuard = guards.find { it.name == guard.name }!!
        posts.find { it.name == shift.post.name }!!
            .addShift(shift.startTime, shift.endTime, assignedGuard)
    }

    fun unAssign(guard: Guard, shift: Shift) {
        val assignedGuard = guards.find { it.name == guard.name }!!
        assignedGuard.shifts.removeIf {
            it.startTime.compareTo(shift.startTime) == 0 && it.endTime.compareTo(shift.endTime) == 0
        }
        posts.find { it.name == shift.post.name }!!
            .shifts.removeIf {
                it.startTime.compareTo(shift.startTime) == 0 && it.endTime.compareTo(shift.endTime) == 0
            }
    }
}
