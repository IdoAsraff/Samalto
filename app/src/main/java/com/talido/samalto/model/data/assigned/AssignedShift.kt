package com.talido.samalto.model.data.assigned

import java.util.*

data class AssignedShift(
    val postName: String,
    val startTime: Calendar,
    val endTime: Calendar,
    val assignedGuardName: String
)