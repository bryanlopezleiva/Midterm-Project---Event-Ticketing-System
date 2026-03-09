package com.example.project_1.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TicketType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticket_type_id;

  private String name;
  private Integer price;
  private Integer quantity_available;

  /// optimistic locking
  /// foreign key to event id
}
