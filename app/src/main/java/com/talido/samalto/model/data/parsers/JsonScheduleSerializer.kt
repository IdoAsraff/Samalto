package com.talido.samalto.model.data.parsers

import com.google.gson.Gson
import com.talido.samalto.model.data.assigned.Schedule

class JsonScheduleSerializer: ScheduleSerializer {
    override fun serialize(schedule: Schedule): String = Gson().toJson(schedule)

    override fun deserialize(data: String): Schedule = Gson().fromJson(data, Schedule::class.java)
}