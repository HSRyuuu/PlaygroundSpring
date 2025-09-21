package com.hsryuuu.traffic.kafkaevent.participation.dto

import java.util.*

data class ParticipationRequestMessage(
    val eventId: UUID,
    val userId: String
)