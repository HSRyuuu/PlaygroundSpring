package com.hsryuuu.traffic.`kafka-event`.participation;

import com.fasterxml.jackson.databind.ObjectMapper
import com.hsryuuu.traffic.domain.event.participation.dto.ParticipationRequestMessage

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ParticipationConsumer(
    private val repository: EventParticipationRepository,
    private val objectMapper: ObjectMapper
) {
    @KafkaListener(topics = ["event-participation"], groupId = "event-consumer-group")
    fun consume(record: ConsumerRecord<String, String>) {
        val json = record.value()
        println("📩 Kafka 메시지 수신: ${LocalDateTime.now()}")
        println(json)

        val message = objectMapper.readValue(json, ParticipationRequestMessage::class.java)

        val participation = com.hsryuuu.traffic.`kafka-event`.participation.EventParticipation(
            eventId = message.eventId,
            userId = message.userId
        )
        repository.save(participation)

        println("✅ DB 저장 완료: $participation")
    }
}