package com.example.project_1.repository;


import com.example.project_1.entity.Event;
import com.example.project_1.entity.EventStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(EventStatus status);

    @Query("SELECT SUM(tt.price) FROM Booking b JOIN b.ticketType tt WHERE tt.event.event_id = :eventId AND b.paymentStatus = 'CONFIRMED'")
    BigDecimal calculateConfirmedRevenue(@Param("eventId") Long eventId);
}
