package com.talido.samalto.model.data.assigned

import java.time.LocalTime

data class AssignedShift(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val assignedGuard: AssignedGuard
)