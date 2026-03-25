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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service

public class EventService  {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private VenueRepository venueRepository;


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
        return mapToEventResponseDTO(saved);
    }

    public List <EventResponseDTO> getAllUpcomingEvents()
    {
        return  eventRepository.findByStatus(EventStatus.UPCOMING)
                .stream()
                .map(this::mapToEventResponseDTO)
                .collect(Collectors.toList());
    }

    public EventResponseDTO getEventById(Long id)
    {
                Event event = eventRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Event not found"));
                return mapToEventResponseDTO(event);

    }

    public RevenueDTO getEventRevenue(Long eventId)
    {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        BigDecimal revenue = eventRepository.calculateConfirmedRevenue(eventId);
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setEventId(eventId);
        revenueDTO.setEventName(event.getTitle());
        revenueDTO.setTotalRevenue(revenue != null ? revenue : BigDecimal.ZERO);
        return revenueDTO;

    }

    private EventResponseDTO mapToEventResponseDTO(Event event)
    {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setEventId(event.getEventId());
        dto.setEventName(event.getTitle());
        dto.setEventDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setEventStatus(event.getStatus());
        dto.setOrganizerName(event.getOrganizer().getName());
        dto.setVenueName(event.getVenue().getName());
        dto.setTicketTypes(event.getTicketTypes().stream().map(ticketType ->
        {
            TicketTypeDTO ticketTypeDTO = new TicketTypeDTO();
            ticketTypeDTO.setTicketTypeId(ticketType.getTicketTypeId());
            ticketTypeDTO.setName(ticketType.getName());
            ticketTypeDTO.setPrice(ticketType.getPrice());
            ticketTypeDTO.setQuantityAvailable(ticketType.getQuantityAvailable());
            return ticketTypeDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
