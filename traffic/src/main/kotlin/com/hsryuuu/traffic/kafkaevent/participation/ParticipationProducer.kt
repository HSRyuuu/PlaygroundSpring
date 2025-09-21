package com.hsryuuu.traffic.kafkaevent.participation

import com.fasterxml.jackson.databind.ObjectMapper
import com.hsryuuu.traffic.kafkaevent.participation.dto.ParticipationRequestMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ParticipationProducer(
    private val kafkaTemplate : KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    companion object {
        private const val TOPIC = "event-participation"
    }

    fun send(message: ParticipationRequestMessage){
        val json = objectMapper.writeValueAsString(message)
        kafkaTemplate.send(TOPIC, json)
        println("✅ Kafka 메시지 전송: ${LocalDateTime.now()}")
        println(json)
    }
}