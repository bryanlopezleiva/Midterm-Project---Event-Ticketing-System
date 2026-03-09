package com.example.project_1.dto;

import lombok.Data;

@Data
public class TicketTypeDTO {
    private Long ticket_Type_ID;
    private String name;
    private double price;
    private Integer quantity_available;
}
