package com.example.project_1.controller;

import com.example.project_1.dto.AttendeeBookingsDTO;
import com.example.project_1.dto.AttendeeResponseDTO;
import com.example.project_1.dto.BookingResponseDTO;
import com.example.project_1.entity.Attendee;
import com.example.project_1.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    // POST / Create a new attendee
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AttendeeResponseDTO createAttendee(@RequestBody Attendee attendee)
    {
        return attendeeService.createAttendee(attendee);
    }

    // GET / get all the bookings for an attendee
    @GetMapping("/{id}/bookings")
    public AttendeeBookingsDTO getAttendeeBookings(@PathVariable Long id) {
        return attendeeService.getAttendeeBookings(id);
    }
}
