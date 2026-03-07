package com.example.project_1.dto;

import lombok.Data;
import java.util.List;

@Data
public class AttendeeBookingsDTO {
    private Long attendee_Id;
    private String attendee_Name;
    private List<BookingResponseDTO> bookings;
}
