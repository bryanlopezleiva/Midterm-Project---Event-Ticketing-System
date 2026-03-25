package com.example.project_1.controller;

import com.example.project_1.entity.Organizer;
import com.example.project_1.service.OrganizerService;
import com.example.project_1.dto.OrganizerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;


    // POST /api/organizers — creates a new organizer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizerResponseDTO createOrganizer(@RequestBody Organizer organizer) {
        return organizerService.createOrganizer(organizer); // returns 201
    }

    // GET /api/organizers — returns all organizers
    @GetMapping
    public List<OrganizerResponseDTO> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    // GET /api/organizers/{id} — returns one organizer by ID
    @GetMapping("/{id}")
    public OrganizerResponseDTO getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id);
    }
}
