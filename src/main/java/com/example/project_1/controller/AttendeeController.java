package com.example.project_1.controller;

import com.example.project_1.dto.AttendeeBookingsDTO;
import com.example.project_1.dto.AttendeeResponseDTO;
import com.example.project_1.entity.Attendee;
import com.example.project_1.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService){
        this.attendeeService = attendeeService;
    }

    // POST / Create a new attendee
    @PostMapping
    public ResponseEntity<AttendeeResponseDTO> createAttendee(@RequestBody Attendee attendee){
        return new ResponseEntity<>(attendeeService.createAttendee(attendee), HttpStatus.CREATED);
    }

    // GET / get all the bookings for an attendee
    @GetMapping("/{id}/bookings")
    public ResponseEntity<AttendeeBookingsDTO> getAttendeeBookings(@PathVariable Long id){
        return ResponseEntity.ok(attendeeService.getAttendeeBookings(id));
    }
}
