package com.example.project_1.service;

import com.example.project_1.dto.EventResponseDTO;
import com.example.project_1.dto.RevenueDTO;
import com.example.project_1.dto.TicketTypeDTO;
import com.example.project_1.entity.*;
import com.example.project_1.repository.BookingRepository;
import com.example.project_1.repository.EventRepository;
import com.example.project_1.repository.OrganizerRepository;
import com.example.project_1.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EventService  {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;
    private final BookingRepository bookingRepository;

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
        BigDecimal revenue = bookingRepository.calculateConfirmedRevenue(eventId);
        RevenueDTO revenueDTO = new RevenueDTO();
        revenueDTO.setEventId(eventId);
        revenueDTO.setEventName(event.getTitle());
        revenueDTO.setTotalRevenue(revenue != null ? revenue : BigDecimal.ZERO);
        return revenueDTO;

    }

    private EventResponseDTO mapToDTO(Event event)
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