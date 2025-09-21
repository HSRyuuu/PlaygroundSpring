package com.hsryuuu.traffic.kafkaevent.event.dto

import com.hsryuuu.traffic.kafkaevent.event.type.RewardType
import java.time.LocalDateTime

data class EventCreateRequest(
    val title: String,
    val description: String?,
    val rewardType: RewardType,
    val rewardQuantity: Int,
    val maxAttemptsPerUser: Int = 1,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime
)

