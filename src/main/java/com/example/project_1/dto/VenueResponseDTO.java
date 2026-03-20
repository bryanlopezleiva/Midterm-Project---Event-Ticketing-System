package com.example.project_1.dto;

import lombok.Data;

@Data
public class VenueResponseDTO {
    private Long venue_id;
    private String name;
    private String address;
    private String city;
    private Integer total_capacity;
}
