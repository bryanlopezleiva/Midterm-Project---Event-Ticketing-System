package com.example.project_1.service;

import com.example.project_1.entity.Venue;
import com.example.project_1.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Transactional
    //create Venue
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    // get all Venues
    public List<Venue> getAllVenue() {
        return venueRepository.findAll();
    }

    // get Venue by ID Else throw message "Venue not found"
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Venue with id " + id + " does not exist"));
    }
}
