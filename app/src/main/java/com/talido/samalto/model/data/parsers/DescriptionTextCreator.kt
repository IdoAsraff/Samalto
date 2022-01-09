package com.talido.samalto.model.data.parsers

import com.talido.samalto.model.data.assigned.Schedule
import java.util.*

class DescriptionTextCreator {
    fun createByGuard(schedule: Schedule): String = buildString {
        for (guard in schedule.guards) {
            append("${guard.name}:\n")
            if (guard.shifts.isEmpty()) {
                append("אין משמרות\n")
            }
            for (shift in guard.shifts) {
                append(
                    "  ${shift.postName}: ${shiftTimeToString(shift.startTime)} - ${
                        shiftTimeToString(
                            shift.endTime
                        )
                    }\n"
                )
            }
        }
    }

    fun createByPost(schedule: Schedule): String = buildString {
        for (post in schedule.posts) {
            append("${post.name}:\n")
            if (post.shifts.isEmpty()) {
                append("אין משמרות\n")
            }
            for (shift in post.shifts) {
                append(
                    "  ${shift.assignedGuardName}: ${shiftTimeToString(shift.startTime)} - ${
                        shiftTimeToString(
                            shift.endTime
                        )
                    }\n"
                )
            }
        }
    }

    private fun shiftTimeToString(time: Calendar): String {
        val minutesField = time.get(Calendar.MINUTE).toString().padStart(2, '0')
        val dayField = toHebrewDay(time.get(Calendar.DAY_OF_WEEK))
        val hourField = time.get(Calendar.HOUR_OF_DAY)
        return "$dayField $hourField:$minutesField"
    }

    private fun toHebrewDay(dayIndex: Int): String = when (dayIndex) {
        0 -> "ראשון"
        1 -> "שני"
        2 -> "שלישי"
        3 -> "רביעי"
        4 -> "חמישי"
        5 -> "שישי"
        6 -> "שבת"
        else -> throw IllegalStateException("No such day of the week index: $dayIndex")
    }
}