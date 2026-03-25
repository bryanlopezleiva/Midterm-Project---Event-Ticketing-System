package com.example.project_1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RevenueDTO {
    private Long eventId;
    private String eventName;
    private BigDecimal totalRevenue;
}
