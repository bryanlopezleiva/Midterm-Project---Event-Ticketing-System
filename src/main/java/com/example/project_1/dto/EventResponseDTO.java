package com.example.project_1.dto;

import com.example.project_1.entity.EventStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventResponseDTO {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventDate;
    private EventStatus eventStatus;

    private String organizerName;
    private String venueName;
    private List<TicketTypeDTO> ticketTypes;
}
