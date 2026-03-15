package com.example.project_1.repository;

import com.example.project_1.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByAttendee_attendee_Id(Long attendeeId);

    boolean existsByAttendee_Attendee_idAndTicketType_Ticket_type_id(Long attendeeId, Long ticketTypeId);

    @Query("SELECT SUM(b.ticketType.price) FROM Booking b "
            + "WHERE b.ticketType.event.event_id = :event_id AND b.paymentStatus = 'CONFIRMED'")
    Double calculateConfirmedRevenue(@Param("event_id") Long event_id);


}
