package com.talido.samalto.model

import com.talido.samalto.model.data.Guard
import com.talido.samalto.model.data.Post
import com.talido.samalto.model.data.assigned.AssignedGuard
import com.talido.samalto.model.data.assigned.Schedule
import com.talido.samalto.model.data.assigned.ShiftAdditionException
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*

class Scheduler {
    @ExperimentalStdlibApi
    fun schedule(startTime: Calendar, posts: List<Post>, guards: List<Guard>): Schedule {
        val schedule = Schedule(startTime, posts, guards)

        for (post in posts) {
            for (shift in post.shifts) {
                var highestRankingGuard = guards[0]
                var highestRanking = 0
                for (guard in guards) {
                    try {
                        schedule.assign(guard, shift)
                        val ranking = rank(schedule)
                        if (highestRanking < ranking) {
                            highestRanking = ranking
                            highestRankingGuard = guard
                        }
                        schedule.unAssign(guard, shift)
                    } catch (e: ShiftAdditionException) {
                        // cannot assign this shift to this guard - try with next one
                    }
                }
                schedule.assign(highestRankingGuard, shift)
            }
        }

        return schedule
    }

    private fun rank(schedule: Schedule): Int {
        var minSuffering = Int.MAX_VALUE
        var maxSuffering = Int.MIN_VALUE

        for (guard in schedule.guards) {
            minSuffering = min(rankGuardSuffering(guard), minSuffering)
            maxSuffering = max(rankGuardSuffering(guard), maxSuffering)
        }

        return maxSuffering - minSuffering
    }

    private fun rankGuardSuffering(guard: AssignedGuard): Int {
        return guard.shifts.size
    }
}