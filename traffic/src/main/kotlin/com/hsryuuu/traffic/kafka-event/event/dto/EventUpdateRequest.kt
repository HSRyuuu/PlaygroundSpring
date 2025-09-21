package com.hsryuuu.traffic.`kafka-event`.event.dto

import com.hsryuuu.traffic.domain.event.event.type.EventStatus
import com.hsryuuu.traffic.domain.event.event.type.RewardType
import java.time.LocalDateTime

data class EventUpdateRequest(
    val title: String,
    val description: String?,
    val rewardType: RewardType,
    val rewardQuantity: Int,
    val maxAttemptsPerUser: Int,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val status: EventStatus
)