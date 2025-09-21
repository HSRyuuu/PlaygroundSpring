package com.hsryuuu.traffic.kafkaevent.event

import com.hsryuuu.traffic.kafkaevent.event.dto.EventCreateRequest
import com.hsryuuu.traffic.kafkaevent.event.dto.EventResponse
import com.hsryuuu.traffic.kafkaevent.event.dto.EventUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/events")
@RestController
class EventController(private val eventService : EventService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<EventResponse>>{
        val allList = eventService.findAll();
        return ResponseEntity.ok(allList);
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<EventResponse> {
        return ResponseEntity.ok(eventService.findById(id))
    }

    @PostMapping
    fun create(@RequestBody request: EventCreateRequest): ResponseEntity<EventResponse> {
        return ResponseEntity.ok(eventService.create(request))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: EventUpdateRequest): ResponseEntity<EventResponse> {
        return ResponseEntity.ok(eventService.update(id, request))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        eventService.delete(id)
        return ResponseEntity.noContent().build()
    }
}