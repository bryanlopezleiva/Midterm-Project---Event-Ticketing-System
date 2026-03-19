package com.example.project_1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketTypeDTO {
    private Long ticket_Type_ID;
    private String name;
    private BigDecimal price;
    private Integer quantity_available;
}
