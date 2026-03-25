package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;
    private String title;
    private String description;
    @Column(name="event_date")
    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

     /// foreign key Organizer
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private Organizer organizer;
     /// foreign key Venue
    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<TicketType> ticketTypes = new ArrayList<>();
}
