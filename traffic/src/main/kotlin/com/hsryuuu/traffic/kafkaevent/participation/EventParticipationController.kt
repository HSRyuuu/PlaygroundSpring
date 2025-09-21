package com.hsryuuu.traffic.kafkaevent.participation;

import com.hsryuuu.traffic.kafkaevent.participation.dto.ParticipationRequestMessage
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/participation")
class ParticipationTestController(
    private val participationProducer: ParticipationProducer
) {
    @PostMapping
    fun participate(
        @RequestParam eventId: UUID,
        @RequestParam userId: String
    ): String {
        val message = ParticipationRequestMessage(eventId, userId)
        participationProducer.send(message)
        return "메시지 전송 완료";
    }
}