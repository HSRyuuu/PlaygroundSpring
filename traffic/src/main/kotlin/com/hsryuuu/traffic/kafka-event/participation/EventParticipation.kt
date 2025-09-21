package com.hsryuuu.traffic.`kafka-event`.participation;

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "event_participation")
class EventParticipation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "event_id", nullable = false)
    val eventId: UUID,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    @CreatedDate
    @Column(name = "participated_at", nullable = false)
    var participatedAt: LocalDateTime = LocalDateTime.now()
)