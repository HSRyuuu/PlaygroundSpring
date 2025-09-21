package com.hsryuuu.traffic.`kafka-event`.participation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventParticipationRepository: JpaRepository<com.hsryuuu.traffic.`kafka-event`.participation.EventParticipation, Long> {
}