package com.example.project_1.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venue_id;

    @Column(nullable = false)
    private String name;
    private String address;
    private String city;
    private Integer total_capacity;

    @OneToMany(mappedBy = "venue")
    private List<Event> events = new ArrayList<>();
}
