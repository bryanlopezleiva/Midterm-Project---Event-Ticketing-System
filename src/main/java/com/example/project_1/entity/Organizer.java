package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Organizer")
@Data
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="organizer_id")
    private Long organizerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String number;

    @OneToMany(mappedBy = "organizer")
    private List<Event> events = new ArrayList<>();
}
