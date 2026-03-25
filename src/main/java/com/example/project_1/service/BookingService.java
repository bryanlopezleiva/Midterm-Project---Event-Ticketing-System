package com.example.project_1.service;

import com.example.project_1.dto.BookingResponseDTO;
import com.example.project_1.entity.*;
import com.example.project_1.repository.AttendeeRepository;
import com.example.project_1.repository.BookingRepository;
import com.example.project_1.repository.TicketTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    /// this will be the POST into /api/bookings
    @Transactional
    public BookingResponseDTO createBooking(Long attendeeId, Long ticketTypeId)
    {
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found."));

        if (ticketType.getQuantityAvailable() <= 0)
        {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }

        /// now we need to check if the Attendee exists
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));

        /// now we must check if attendee has not booked the same ticket type
        boolean alreadyBooked = bookingRepository.existsByAttendee_Attendee_idAndTicketType_Ticket_type_id(attendeeId, ticketTypeId);

        if(alreadyBooked)
        {
            throw new RuntimeException("You have already booked this ticket type");
        }

        /// now we need to decrement the quantity available by 1
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() - 1);
        ticketTypeRepository.save(ticketType);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentStatus(PaymentStatus.CONFIRMED);
        booking.setBookingReference("");

        Booking saved = bookingRepository.save(booking);

        /// generating a unique booking reference in the format TKT-{year}-{zero-padded booking_id}
        //  for example TKT-2025-00042
        String reference = String.format("TKT-%d-%05d", LocalDateTime.now().getYear(), saved.getBookingId());
        saved.setBookingReference(reference);
        bookingRepository.save(saved);

        return mapToBookingResponseDTO(saved);
    }

    /// now we need to cancel a booking - PUT/api/bookings/{id}/ cancel
    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId)
    {
        /// need to verify the booking exists and is not already canceled
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking does not exists."));

        if (booking.getPaymentStatus() == PaymentStatus.CANCELLED)
        {
            throw new RuntimeException("Booking is already canceled");
        }

        /// now we need to set the booking status to cancel and increment the quantity available by one
        booking.setPaymentStatus(PaymentStatus.CANCELLED);
        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantityAvailable(ticketType.getQuantityAvailable() + 1);
        bookingRepository.save(booking);
        ticketTypeRepository.save(ticketType);

        return mapToBookingResponseDTO(booking);
    }

    private BookingResponseDTO mapToBookingResponseDTO(Booking booking)
    {
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
}
