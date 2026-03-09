package com.example.project_1.dto;

import com.example.project_1.entitiy.EventStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventResponseDTO {
    private Long event_Id;
    private String event_Name;
    private String event_Description;
    private LocalDateTime event_Date;
    private EventStatus event_Status;

    private String organizer_Name;
    private String venue_Name;
    private List<TicketTypeDTO> ticket_Types;
}
