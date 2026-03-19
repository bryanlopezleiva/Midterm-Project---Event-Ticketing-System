package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_type_id;

    private String name;
    private BigDecimal price;
    private Integer quantity_available;

    /// optimistic locking
    /// foreign key to event id
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}