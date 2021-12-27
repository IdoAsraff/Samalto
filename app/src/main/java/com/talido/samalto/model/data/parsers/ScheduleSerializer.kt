package com.talido.samalto.model.data.parsers

import com.talido.samalto.model.data.assigned.Schedule

interface ScheduleSerializer {
    fun serialize(schedule: Schedule): String

    fun deserialize(data: String): Schedule
}