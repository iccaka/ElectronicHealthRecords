package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.entities.Calendar;
import com.group12.ElectronicHealthRecords.repositories.CalendarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public Optional<Calendar> getAppointmentById(Long id) {
        return calendarRepository.findById(id);
    }

    public List<Calendar> getAppointments() {
        return calendarRepository.findAll();
    }

    public List<Calendar> findAppointmentsInPeriod(LocalDateTime start, LocalDateTime end) {
        return calendarRepository.findAppointmentsInRange(start, end);
    }

    public boolean hasOverlappingAppointments(Calendar calendar) {
        LocalDateTime start = calendar.getDate();
        LocalDateTime end = start.plusHours(calendar.getDuration());
        Integer overlapping = calendarRepository.findOverlappingAppointments(start, end);
        return overlapping != 0;
    }

    public Calendar createAppointment(Calendar calendar) {
        if (this.hasOverlappingAppointments(calendar)) {
            throw new IllegalArgumentException("Appointments already exist in the given period");
        }
        return calendarRepository.save(calendar);
    }

    public void deleteAppointment(Long id) {
        calendarRepository.findById(id).ifPresent(appointment -> calendarRepository.delete(appointment));
    }
}
