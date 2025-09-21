package com.hsryuuu.traffic.domain.event.event.dto

import com.hsryuuu.traffic.domain.event.event.Event
import com.hsryuuu.traffic.domain.event.event.type.EventStatus
import com.hsryuuu.traffic.domain.event.event.type.RewardType
import java.time.LocalDateTime
import java.util.*

data class EventResponse(
    val id: UUID,
    val title: String,
    val description: String?,
    val rewardType: RewardType,
    val rewardQuantity: Int,
    val maxAttemptsPerUser: Int,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val status: EventStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(event: Event): EventResponse {
            return EventResponse(
                id = event.id,
                title = event.title,
                description = event.description,
                rewardType = event.rewardType,
                rewardQuantity = event.rewardQuantity,
                maxAttemptsPerUser = event.maxAttemptsPerUser,
                startAt = event.startAt,
                endAt = event.endAt,
                status = event.status,
                createdAt = event.createdAt,
                updatedAt = event.updatedAt
            )
        }
    }
}