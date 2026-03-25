package com.example.project_1.service;

import com.example.project_1.dto.TicketTypeDTO;
import com.example.project_1.entity.Event;
import com.example.project_1.entity.TicketType;
import com.example.project_1.repository.EventRepository;
import com.example.project_1.repository.TicketTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final EventRepository eventRepository;

    public TicketTypeService(TicketTypeRepository ticketTypeRepository,
                             EventRepository eventRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
        this.eventRepository = eventRepository;
    }

    // POST /api/events/{eventId}/tickettypes — create a ticket type for an event
    @Transactional
    public TicketTypeDTO createTicketType(Long eventId, TicketType ticketType) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        ticketType.setEvent(event);
        TicketType saved = ticketTypeRepository.save(ticketType);
        return mapToDTO(saved);
    }

    // GET /api/events/{eventId}/tickettypes — get all ticket types for an event
    public List<TicketTypeDTO> getTicketTypesByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return event.getTicketTypes()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private TicketTypeDTO mapToDTO(TicketType ticketType) {
        TicketTypeDTO dto = new TicketTypeDTO();
        dto.setTicketTypeId(ticketType.getTicketTypeId());
        dto.setName(ticketType.getName());
        dto.setPrice(ticketType.getPrice());
        dto.setQuantityAvailable(ticketType.getQuantityAvailable());
        return dto;
    }
}