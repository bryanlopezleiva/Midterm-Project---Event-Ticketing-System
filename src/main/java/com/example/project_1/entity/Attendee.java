package com.example.project_1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="attendee_id")
    private Long attendeeId;
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
}
