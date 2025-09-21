package com.hsryuuu.traffic.kafkaevent.event

import com.hsryuuu.traffic.kafkaevent.event.dto.EventCreateRequest
import com.hsryuuu.traffic.kafkaevent.event.dto.EventResponse
import com.hsryuuu.traffic.kafkaevent.event.dto.EventUpdateRequest
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class EventService(private val eventRepository: EventRepository) {

    fun findAll() : List<EventResponse> =
        eventRepository.findAll().map{ EventResponse.from(it) }

    fun findById(id: UUID): EventResponse =
        eventRepository.findById(id)
            .map { EventResponse.from(it) }
            .orElseThrow { EntityNotFoundException("이벤트를 찾을 수 없습니다.") }

    @Transactional
    fun create(request: EventCreateRequest): EventResponse {
        val event = Event(
            title = request.title,
            description = request.description,
            rewardType = request.rewardType,
            rewardQuantity = request.rewardQuantity,
            maxAttemptsPerUser = request.maxAttemptsPerUser,
            startAt = request.startAt,
            endAt = request.endAt
        )
        return EventResponse.from(eventRepository.save(event))
    }

    @Transactional
    fun update(id: UUID, request: EventUpdateRequest): EventResponse {
        val event = eventRepository.findById(id).orElseThrow {
            EntityNotFoundException("이벤트를 찾을 수 없습니다.")
        }

        event.apply {
            title = request.title
            description = request.description
            rewardType = request.rewardType
            rewardQuantity = request.rewardQuantity
            maxAttemptsPerUser = request.maxAttemptsPerUser
            startAt = request.startAt
            endAt = request.endAt
            status = request.status
        }

        return EventResponse.from(eventRepository.save(event))
    }

    @Transactional
    fun delete(id: UUID) {
        eventRepository.deleteById(id)
    }
}