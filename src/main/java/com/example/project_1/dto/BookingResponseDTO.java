package com.example.project_1.dto;

import com.example.project_1.entity.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long bookingId;
    private String bookingReference;
    private LocalDateTime bookingDate;
    private PaymentStatus paymentStatus;
    private String attendeeName;
    private String eventTitle;
    private String ticketTypeName;
    private BigDecimal price;
}
