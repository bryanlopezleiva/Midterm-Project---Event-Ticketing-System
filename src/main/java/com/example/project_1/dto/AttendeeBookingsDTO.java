package com.example.project_1.dto;

import lombok.Data;
import java.util.List;

@Data
public class AttendeeBookingsDTO {
    private Long attendeeId;
    private String attendeeName;
    private List<BookingResponseDTO> bookings;
}
