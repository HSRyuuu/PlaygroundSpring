package com.hsryuuu.traffic.`kafka-event`.event

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventRepository : JpaRepository<com.hsryuuu.traffic.`kafka-event`.event.Event, UUID> {
}