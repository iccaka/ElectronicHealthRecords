package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.entities.Calendar;
import com.group12.ElectronicHealthRecords.services.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/appointment/all")
    public List<Calendar> findAllAppointments() {
        return calendarService.getAppointments();
    }

    @GetMapping("/appointment")
    public Optional<Calendar> findAllAppointments(@RequestParam Long id) {
        return calendarService.getAppointmentById(id);
    }

    @PostMapping("/appointment/save")
    public Calendar createAppointment(@RequestBody Calendar calendar) {
        return calendarService.createAppointment(calendar);
    }

    @DeleteMapping("/appointment/delete")
    public void deleteAppointment(@RequestParam Long id) {
        calendarService.deleteAppointment(id);
    }

}
