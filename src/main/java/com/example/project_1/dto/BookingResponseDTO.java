package com.example.project_1.dto;

import com.example.project_1.entity.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long booking_Id;
    private String booking_Reference;
    private LocalDateTime booking_Date;
    private PaymentStatus payment_Status;

    private String attendee_Name;

    private String event_Name;
    private String ticket_Type;
    private BigDecimal price;

}
