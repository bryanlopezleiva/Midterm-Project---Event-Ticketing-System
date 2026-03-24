package com.example.project_1.repository;

import com.example.project_1.entity.Booking;
import com.example.project_1.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.attendee.attendee_id = :attendeeId")
    List<Booking> findByAttendee_attendee_Id(Long attendeeId);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END "
            + "FROM Booking b WHERE b.attendee.attendee_id = :attendeeId "
            + "AND b.ticketType.ticketTypeId = :ticketTypeId")
    boolean existsByAttendee_Attendee_idAndTicketType_Ticket_type_id(@Param("attendeeId") Long attendeeId, @Param("ticketTypeId") Long ticketTypeId);
}