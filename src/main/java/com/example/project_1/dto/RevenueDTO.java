package com.example.project_1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RevenueDTO {
    private Long event_Id;
    private String event_Name;
    private BigDecimal total_Revenue;
}
