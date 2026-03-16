package com.example.project_1.service;

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
    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    // Get all Organizers
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    // Get Organizer by ID else Organizer with {ID} doesnt exist
    public Organizer getOrganizerById(long id) {
        return organizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizer with id " + id + " does not exist"));
    }
}
