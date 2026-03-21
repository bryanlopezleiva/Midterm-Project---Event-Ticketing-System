package com.example.project_1.service;

import com.example.project_1.dto.OrganizerResponseDTO;
import com.example.project_1.entity.Organizer;
import com.example.project_1.repository.OrganizerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Transactional
    // Create a new Organizer
    public OrganizerResponseDTO createOrganizer(Organizer organizer) {
        Organizer saved = organizerRepository.save(organizer);
        return mapToDTO(saved);
    }

    // Get all Organizers
    public List<OrganizerResponseDTO> getAllOrganizers() {
        return organizerRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // Get Organizer by ID else Organizer with {ID} doesn't exist
    public OrganizerResponseDTO getOrganizerById(long id) {
        Organizer organizer = organizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizer with id " + id + " does not exist"));
        return mapToDTO(organizer);
    }

    private OrganizerResponseDTO mapToDTO(Organizer organizer) {
        OrganizerResponseDTO dto = new OrganizerResponseDTO();
        dto.setOrganizer_id(organizer.getOrganizer_id());
        dto.setName(organizer.getName());
        dto.setEmail(organizer.getEmail());
        dto.setNumber(organizer.getNumber());
        return dto;
    }
}
