package com.example.project_1.dto;

import lombok.Data;

@Data
public class VenueResponseDTO {
    private Long venueId;
    private String name;
    private String address;
    private String city;
    private Integer totalCapacity;
}
