package com.talido.samalto.model.data.assigned

import java.time.LocalTime

data class AssignedShift(
    val post: AssignedPost,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val assignedGuard: AssignedGuard
)