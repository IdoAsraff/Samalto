package com.talido.samalto.model.data

import java.time.LocalTime

data class Shift(val post: Post, val startTime: LocalTime, val endTime: LocalTime)