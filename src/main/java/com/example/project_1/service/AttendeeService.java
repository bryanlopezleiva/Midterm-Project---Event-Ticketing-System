package com.example.project_1.service;


import com.example.project_1.dto.AttendeeBookingsDTO;
import com.example.project_1.dto.AttendeeResponseDTO;
import com.example.project_1.dto.BookingResponseDTO;
import com.example.project_1.entity.Attendee;
import com.example.project_1.entity.Booking;
import com.example.project_1.repository.AttendeeRepository;
import com.example.project_1.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
public class AttendeeService {

    @Autowired
    private  AttendeeRepository attendeeRepository;

    @Autowired
    private  BookingRepository bookingRepository;

    // POST / Create a new attendee
    @Transactional
    public AttendeeResponseDTO createAttendee(Attendee attendee){
        // Check the email before saving
        if(attendeeRepository.existsByEmail(attendee.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        Attendee saved = attendeeRepository.save(attendee);
        AttendeeResponseDTO dto = new AttendeeResponseDTO();
        dto.setAttendeeId(saved.getAttendeeId());
        dto.setName(saved.getName());
        dto.setEmail(saved.getEmail());
        return dto;
    }

    // GET / Get all the bookings for an attendee
    public AttendeeBookingsDTO getAttendeeBookings(Long attendeeId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee with id: " + attendeeId + " does not exist"));

        // Get all bookings for this attendee and map each one to a DTO
        List<BookingResponseDTO> bookingDTOs = bookingRepository.findByAttendee_attendee_Id(attendeeId)
                .stream().map(this::mapToBookingResponseDTO).toList();

        // Wrap everything in AttendeeBookingsDTO
        return mapToAttendeeBookingsDTO(attendee, bookingDTOs);
    }

    private AttendeeResponseDTO mapToAttendeeResponseDTO(Attendee attendee) {
        AttendeeResponseDTO dto = new AttendeeResponseDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setName(attendee.getName());
        dto.setEmail(attendee.getEmail());
        return dto;
    }

    private BookingResponseDTO mapToBookingResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setBookingReference(booking.getBookingReference());
        dto.setBookingDate(booking.getBookingDate());
        dto.setPaymentStatus(booking.getPaymentStatus());
        dto.setAttendeeName(booking.getAttendee().getName());
        dto.setEventTitle(booking.getTicketType().getEvent().getTitle());
        dto.setTicketTypeName(booking.getTicketType().getName());
        dto.setPrice(booking.getTicketType().getPrice());
        return dto;
    }

    private AttendeeBookingsDTO mapToAttendeeBookingsDTO(Attendee attendee, List<BookingResponseDTO> bookings) {
        AttendeeBookingsDTO dto = new AttendeeBookingsDTO();
        dto.setAttendeeId(attendee.getAttendeeId());
        dto.setAttendeeName(attendee.getName());
        dto.setBookings(bookings);
        return dto;
    }

}
