package com.talido.samalto.model.data.assigned

import java.util.*

data class AssignedShift(
    val post: AssignedPost,
    val startTime: Calendar,
    val endTime: Calendar,
    val assignedGuard: AssignedGuard
)