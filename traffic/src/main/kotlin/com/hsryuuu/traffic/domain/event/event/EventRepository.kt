package com.hsryuuu.traffic.domain.event.event

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventRepository : JpaRepository<Event, UUID> {
}