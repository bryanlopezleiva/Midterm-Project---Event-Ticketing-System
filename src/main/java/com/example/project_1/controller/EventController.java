package com.example.project_1.controller;

import com.example.project_1.dto.EventResponseDTO;
import com.example.project_1.dto.RevenueDTO;
import com.example.project_1.entity.Event;
import com.example.project_1.service.EventService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/events")

public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService)
    {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody Event event,
            @RequestParam Long organizerId,
            @RequestParam Long venueId)
    {
        EventResponseDTO created = eventService.createEvent(event, organizerId, venueId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllUpcomingEvents()
    {
        return  ResponseEntity.ok(eventService.getAllUpcomingEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEventById(@PathVariable Long id)
        {
        return  ResponseEntity.ok(eventService.getEventById(id));
        }

    @GetMapping("/{id}/revenue")
    public ResponseEntity<RevenueDTO> getEventRevenue(@PathVariable Long id)
    {
        return  ResponseEntity.ok(eventService.getEventRevenue(id));
    }
}
