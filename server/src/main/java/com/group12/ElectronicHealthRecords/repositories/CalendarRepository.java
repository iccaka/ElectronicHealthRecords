package com.group12.ElectronicHealthRecords.repositories;

import com.group12.ElectronicHealthRecords.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query("SELECT c FROM Calendar c WHERE c.date > :start AND c.date < :end")
    List<Calendar> findAppointmentsInRange(LocalDateTime start, LocalDateTime end);

    @Query("SELECT COUNT(c) from Calendar c WHERE c.date < :end AND DATEADD(HOUR, c.duration, c.date) > :start")
    Integer findOverlappingAppointments(LocalDateTime start, LocalDateTime end);
}
