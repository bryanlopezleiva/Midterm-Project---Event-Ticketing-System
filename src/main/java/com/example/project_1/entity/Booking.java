package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/// Junction Table
@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    private String booking_reference;
    private LocalDateTime booking_date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    /// foreign key to attendee id
    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;

    /// foreign key to ticket type id
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;
}
