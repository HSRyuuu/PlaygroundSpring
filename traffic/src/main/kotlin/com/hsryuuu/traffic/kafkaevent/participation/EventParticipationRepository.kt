package com.hsryuuu.traffic.kafkaevent.participation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventParticipationRepository: JpaRepository<EventParticipation, Long> {
}