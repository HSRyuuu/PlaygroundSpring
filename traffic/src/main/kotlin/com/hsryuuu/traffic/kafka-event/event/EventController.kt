package com.hsryuuu.traffic.`kafka-event`.event

import com.hsryuuu.traffic.`kafka-event`.event.dto.EventCreateRequest
import com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse
import com.hsryuuu.traffic.`kafka-event`.event.dto.EventUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/events")
@RestController
class EventController(private val eventService : com.hsryuuu.traffic.`kafka-event`.event.EventService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse>>{
        val allList = eventService.findAll();
        return ResponseEntity.ok(allList);
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse> {
        return ResponseEntity.ok(eventService.findById(id))
    }

    @PostMapping
    fun create(@RequestBody request: com.hsryuuu.traffic.`kafka-event`.event.dto.EventCreateRequest): ResponseEntity<com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse> {
        return ResponseEntity.ok(eventService.create(request))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: com.hsryuuu.traffic.`kafka-event`.event.dto.EventUpdateRequest): ResponseEntity<com.hsryuuu.traffic.`kafka-event`.event.dto.EventResponse> {
        return ResponseEntity.ok(eventService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        eventService.delete(id)
        return ResponseEntity.noContent().build()
    }
}