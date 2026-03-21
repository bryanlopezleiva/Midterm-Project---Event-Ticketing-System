package com.example.project_1.controller;

import com.example.project_1.entity.Organizer;
import com.example.project_1.service.OrganizerService;
import com.example.project_1.dto.OrganizerResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    // Constructor — Spring injects the OrganizerService automatically
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    // POST /api/organizers — creates a new organizer
    @PostMapping
    public ResponseEntity<OrganizerResponseDTO> createOrganizer(@RequestBody Organizer organizer) {
        return new ResponseEntity<>(organizerService.createOrganizer(organizer), HttpStatus.CREATED); // returns 201
    }

    // GET /api/organizers — returns all organizers
    @GetMapping
    public ResponseEntity<List<OrganizerResponseDTO>> getAllOrganizers() {
        return ResponseEntity.ok(organizerService.getAllOrganizers()); // returns 200
    }

    // GET /api/organizers/{id} — returns one organizer by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrganizerResponseDTO> getOrganizerById(@PathVariable Long id) {
        return ResponseEntity.ok(organizerService.getOrganizerById(id)); // returns 200
    }
}