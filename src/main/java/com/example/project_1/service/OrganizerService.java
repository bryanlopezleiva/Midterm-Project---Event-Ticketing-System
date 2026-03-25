package com.example.project_1.service;

import com.example.project_1.dto.OrganizerResponseDTO;
import com.example.project_1.entity.Organizer;
import com.example.project_1.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    @Transactional
    // Create a new Organizer
    public OrganizerResponseDTO createOrganizer(Organizer organizer) {
        if (organizerRepository.existsByEmail(organizer.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Organizer savedOrganizer = organizerRepository.save(organizer);
        return mapToOrganizerResponseDTO(savedOrganizer);
    }

    // Get all Organizers
    public List<OrganizerResponseDTO> getAllOrganizers() {
        return organizerRepository.findAll()
                .stream()
                .map(this::mapToOrganizerResponseDTO)
                .toList();
    }

    // Get Organizer by ID else Organizer with {ID} doesn't exist
    public OrganizerResponseDTO getOrganizerById(long id) {
        Organizer organizer = organizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizer with id " + id + " does not exist"));
        return mapToOrganizerResponseDTO(organizer);
    }

    private OrganizerResponseDTO mapToOrganizerResponseDTO(Organizer organizer) {
        OrganizerResponseDTO dto = new OrganizerResponseDTO();
        dto.setOrganizerId(organizer.getOrganizerId());
        dto.setName(organizer.getName());
        dto.setEmail(organizer.getEmail());
        dto.setNumber(organizer.getNumber());
        return dto;
    }
}
