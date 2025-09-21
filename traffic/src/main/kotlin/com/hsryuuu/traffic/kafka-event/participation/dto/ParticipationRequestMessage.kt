package com.hsryuuu.traffic.`kafka-event`.participation.dto

import java.util.*

data class ParticipationRequestMessage(
    val eventId: UUID,
    val userId: String
)