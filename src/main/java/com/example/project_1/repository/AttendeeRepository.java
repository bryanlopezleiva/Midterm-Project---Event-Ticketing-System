package com.example.project_1.repository;

import com.example.project_1.entity.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    boolean existByEmail(String email);
}
