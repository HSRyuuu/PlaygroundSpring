
package com.hsryuuu.traffic.domain.event

import com.hsryuuu.traffic.domain.event.type.EventStatus
import com.hsryuuu.traffic.domain.event.type.RewardType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "event")
class Event(
    @Id
    @Column(name = "id", nullable = false)
    val id: UUID = UUID.randomUUID(),

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "reward_type", nullable = false)
    var rewardType: RewardType,

    @Column(name = "reward_quantity", nullable = false)
    var rewardQuantity: Int,

    @Column(name = "max_attempts_per_user", nullable = false)
    var maxAttemptsPerUser: Int = 1,

    @Column(name = "start_at", nullable = false)
    var startAt: LocalDateTime,

    @Column(name = "end_at", nullable = false)
    var endAt: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: EventStatus = EventStatus.READY,

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedBy
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)