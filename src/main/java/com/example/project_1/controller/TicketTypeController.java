package com.example.project_1.controller;

import com.example.project_1.dto.TicketTypeDTO;
import com.example.project_1.entity.TicketType;
import com.example.project_1.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    // POST /api/events/{eventId}/tickettypes
    @PostMapping("/{eventId}/tickettypes")
    public ResponseEntity<TicketTypeDTO> createTicketType(
            @PathVariable Long eventId,
            @RequestBody TicketType ticketType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ticketTypeService.createTicketType(eventId, ticketType));
    }

    // GET /api/events/{eventId}/tickettypes
    @GetMapping("/{eventId}/tickettypes")
    public ResponseEntity<List<TicketTypeDTO>> getTicketTypesByEvent(
            @PathVariable Long eventId) {
        return ResponseEntity.ok(ticketTypeService.getTicketTypesByEvent(eventId));
    }
}