package com.example.project_1.controller;

import com.example.project_1.dto.EventResponseDTO;
import com.example.project_1.dto.RevenueDTO;
import com.example.project_1.entity.Event;
import com.example.project_1.service.EventService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/events")

public class EventController {


    @Autowired
    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDTO createEvent(
            @RequestBody Event event,
            @RequestParam Long organizerId,
            @RequestParam Long venueId)
    {
        return eventService.createEvent(event, organizerId, venueId);
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
