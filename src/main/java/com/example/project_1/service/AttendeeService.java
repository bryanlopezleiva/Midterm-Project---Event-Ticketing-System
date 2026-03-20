package com.example.project_1.service;


import com.example.project_1.dto.AttendeeBookingsDTO;
import com.example.project_1.dto.AttendeeResponseDTO;
import com.example.project_1.dto.BookingResponseDTO;
import com.example.project_1.entity.Attendee;
import com.example.project_1.repository.AttendeeRepository;
import com.example.project_1.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final BookingRepository bookingRepository;

    public AttendeeService(AttendeeRepository attendeeRepository, BookingRepository  bookingRepository) {
        this.attendeeRepository = attendeeRepository;
        this.bookingRepository = bookingRepository;
    }

    // POST / Create a new attendee
    @Transactional
    public AttendeeResponseDTO createAttendee(Attendee attendee){
        // Check the email before saving
        if(attendeeRepository.existsByEmail(attendee.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        Attendee saved = attendeeRepository.save(attendee);
        AttendeeResponseDTO dto = new AttendeeResponseDTO();
        dto.setAttendee_Id(saved.getAttendee_id());
        dto.setName(saved.getName());
        dto.setEmail(saved.getEmail());
        return dto;
    }

    // GET / Get all the bookings for an attendee
    public AttendeeBookingsDTO getAttendeeBookings(Long id) {
        Attendee attendee = attendeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendee with id " + id + " does not exist"));

        // Get all bookings for this attendee and map each one to a DTO
        List<BookingResponseDTO> bookingDTOs = bookingRepository
                .findByAttendee_attendee_Id(id)
                .stream()
                .map(booking -> {
                    BookingResponseDTO dto = new BookingResponseDTO();
                    dto.setBooking_Id(booking.getBooking_id());
                    dto.setBooking_Reference(booking.getBooking_reference());
                    dto.setBooking_Date(booking.getBooking_date());
                    dto.setPayment_Status(booking.getPaymentStatus());
                    dto.setAttendee_Name(attendee.getName());
                    // navigate booking → ticketType → event to get event name
                    dto.setEvent_Name(booking.getTicketType().getEvent().getTitle());
                    dto.setTicket_Type(booking.getTicketType().getName());
                    dto.setPrice(booking.getTicketType().getPrice());
                    return dto;
                })
                .toList();
        // Wrap everything in AttendeeBookingsDTO
        AttendeeBookingsDTO result = new AttendeeBookingsDTO();
        result.setAttendee_Id(attendee.getAttendee_id());
        result.setAttendee_Name(attendee.getName());
        result.setBookings(bookingDTOs);
        return result;
    }
}
