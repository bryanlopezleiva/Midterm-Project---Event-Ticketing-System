package com.example.project_1.controller;

import com.example.project_1.dto.VenueResponseDTO;
import com.example.project_1.entity.Venue;
import com.example.project_1.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    @Autowired
    private VenueService venueService;

    // POST /api/organizers — creates a new Venue
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VenueResponseDTO createVenue(@RequestBody Venue venue) {
        return venueService.createVenue(venue);
    }

    // GET /api/organizers — returns all organizers
    @GetMapping
    public ResponseEntity<List<VenueResponseDTO>> getAllVenue() {
        return ResponseEntity.ok(venueService.getAllVenue()); // returns 200
    }

    // GET /api/organizers/{id} — returns one organizer by ID
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponseDTO> getVenueById(@PathVariable Long id) {
            return ResponseEntity.ok(venueService.getVenueById(id)); // returns 200
    }
}
