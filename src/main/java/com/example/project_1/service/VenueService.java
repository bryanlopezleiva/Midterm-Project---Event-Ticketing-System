package com.example.project_1.service;

import com.example.project_1.dto.VenueResponseDTO;
import com.example.project_1.entity.Venue;
import com.example.project_1.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Transactional
    //create Venue
    public VenueResponseDTO createVenue(Venue venue) {
        Venue saved = venueRepository.save(venue);
        return mapToVenueResponseDTO(saved);
    }

    // get all Venues
    public List<VenueResponseDTO> getAllVenue() {
        return venueRepository.findAll()
                .stream()
                .map(this::mapToVenueResponseDTO)
                .toList();
    }

    // get Venue by ID Else throw message "Venue not found"
    public VenueResponseDTO getVenueById(Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Venue with id " + id + " does not exist"));
        return mapToVenueResponseDTO(venue);
    }

    private VenueResponseDTO mapToVenueResponseDTO(Venue venue) {
        VenueResponseDTO dto = new VenueResponseDTO();
        dto.setVenue_id(venue.getVenue_id());
        dto.setName(venue.getName());
        dto.setAddress(venue.getAddress());
        dto.setCity(venue.getCity());
        dto.setTotal_capacity(venue.getTotal_capacity());
        return dto;
    }
}
