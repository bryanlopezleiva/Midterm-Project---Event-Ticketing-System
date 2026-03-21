package com.example.project_1.controller;

import com.example.project_1.dto.BookingResponseDTO;
import com.example.project_1.entity.Booking;
import com.example.project_1.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /// POST /api/bookings Book a ticket See business logic requirements
    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponseDTO createBooking(@RequestParam Long attendeeId, @RequestParam Long ticketTypeId)
    {
        return bookingService.createBooking(attendeeId, ticketTypeId);
    }

    /// PUT api/bookings/{id}/cancel
    // Cancel a booking Restore ticket inventory
    @PutMapping("/bookings/{id}/cancel")
    public BookingResponseDTO cancelBooking(@PathVariable Long id)
    {
        return bookingService.cancelBooking(id);
    }

    /// GET this will return us the revenue from a particular event
    @GetMapping("/events/{id}/revenue")
    public BigDecimal getRevenue(@PathVariable Long id)
    {
        return bookingService.getRevenueByEvent(id);
    }
}
