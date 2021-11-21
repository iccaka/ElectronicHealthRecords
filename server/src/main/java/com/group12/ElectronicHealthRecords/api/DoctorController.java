package com.group12.ElectronicHealthRecords.api;

import lombok.RequiredArgsConstructor;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>>getDoctors() {
        return  ResponseEntity.ok().body(doctorService.getDoctors());
    }

    @PostMapping("/doctor/save")
    public ResponseEntity<Doctor>saveDoctor(@RequestBody Doctor doctor) {
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return  ResponseEntity.ok().body(doctorService.saveDoctor(doctor));
    }
}
