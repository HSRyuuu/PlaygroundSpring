package com.hsryuuu.traffic.`kafka-event`.event

import com.hsryuuu.traffic.`kafka-event`.event.dto.EventCreateRequest
import com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse
import com.hsryuuu.traffic.`kafka-event`.event.dto.EventUpdateRequest
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class EventService(private val eventRepository: com.hsryuuu.traffic.`kafka-event`.event.EventRepository) {

    fun findAll() : List<com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse> =
        eventRepository.findAll().map{ com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse.from(it) }

    fun findById(id: UUID): com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse =
        eventRepository.findById(id)
            .map { com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse.from(it) }
            .orElseThrow { EntityNotFoundException("이벤트를 찾을 수 없습니다.") }

    @Transactional
    fun create(request: com.hsryuuu.traffic.`kafka-event`.event.dto.EventCreateRequest): com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse {
        val event = com.hsryuuu.traffic.`kafka-event`.event.Event(
            title = request.title,
            description = request.description,
            rewardType = request.rewardType,
            rewardQuantity = request.rewardQuantity,
            maxAttemptsPerUser = request.maxAttemptsPerUser,
            startAt = request.startAt,
            endAt = request.endAt
        )
        return com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse.from(eventRepository.save(event))
    }

    @Transactional
    fun update(id: UUID, request: com.hsryuuu.traffic.`kafka-event`.event.dto.EventUpdateRequest): com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse {
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

        return com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse.from(eventRepository.save(event))
    }

    @Transactional
    fun delete(id: UUID) {
        eventRepository.deleteById(id)
    }
}