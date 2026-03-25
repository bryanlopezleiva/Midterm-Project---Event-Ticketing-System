package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="venue_id")
    private Long venueId;

    @Column(nullable = false)
    private String name;
    private String address;
    private String city;
    @Column(name="total_capacity")
    private Integer totalCapacity;

    @OneToMany(mappedBy = "venue")
    private List<Event> events = new ArrayList<>();
}
