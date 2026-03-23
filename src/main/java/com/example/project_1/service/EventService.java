package com.example.project_1.service;

import com.example.project_1.dto.EventResponseDTO;
import com.example.project_1.dto.RevenueDTO;
import com.example.project_1.dto.TicketTypeDTO;
import com.example.project_1.entity.Event;
import com.example.project_1.entity.EventStatus;
import com.example.project_1.entity.Organizer;
import com.example.project_1.entity.Venue;
import com.example.project_1.repository.EventRepository;
import com.example.project_1.repository.OrganizerRepository;
import com.example.project_1.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service

public class EventService  {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    public EventService(EventRepository eventRepository,
                        OrganizerRepository organizerRepository,
                        VenueRepository venueRepository)
    {
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.venueRepository = venueRepository;
    }

    @Transactional

    public EventResponseDTO createEvent(Event event, Long organizerId, Long venueId)
    {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new IllegalArgumentException("Organizer not found"));
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new IllegalArgumentException("Venue not found"));

        event.setOrganizer(organizer);
        event.setVenue(venue);
        event.setStatus(EventStatus.UPCOMING);

        Event saved = eventRepository.save(event);
        return mapToDTO(saved);
    }

    public List <EventResponseDTO> getAllUpcomingEvents()
    {
        return  eventRepository.findByStatus(EventStatus.UPCOMING)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(Long id)
    {
                Event event = eventRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Event not found"));
                return mapToDTO(event);

    }

    public RevenueDTO getEventRevenue(Long eventId)
    {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        Double revenue = eventRepository.calculateConfirmedRevenue(eventId);
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setEvent_Id(eventId);
        revenueDTO.setEvent_Name(event.getTitle());
        revenueDTO.setTotal_Revenue(revenue != null ? BigDecimal.valueOf(revenue) : BigDecimal.ZERO);
        return revenueDTO;

    }

    private EventResponseDTO mapToDTO(Event event)
    {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setEvent_Id(event.getEvent_id());
        dto.setEvent_Name(event.getTitle());
        dto.setEvent_Description(event.getDescription());
        dto.setEvent_Date(event.getEvent_date());
        dto.setEvent_Status(event.getStatus());
        dto.setOrganizer_Name(event.getOrganizer().getName());
        dto.setVenue_Name(event.getVenue().getName());
        dto.setTicket_Types(event.getTicketTypes().stream().map(ticketType ->
        {
            TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
            ticketTypeDTO.setTicket_Type_ID(ticketType.getTicketTypeId());
            ticketTypeDTO.setName(ticketType.getName());
            ticketTypeDTO.setPrice(ticketType.getPrice());
            ticketTypeDTO.setQuantity_available(ticketType.getQuantityAvailable());
            return ticketTypeDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
