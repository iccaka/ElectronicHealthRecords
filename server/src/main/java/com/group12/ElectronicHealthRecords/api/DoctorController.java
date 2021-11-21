package com.group12.ElectronicHealthRecords.api;

import lombok.RequiredArgsConstructor;g
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorController {

    private  final DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>>getDoctors() {
        return  ResponseEntity.ok().body(doctorService.getDoctors());
    }

    @PostMapping("/doctor/save")
    public ResponseEntity<Doctor>saveDoctor(@RequestBody Doctor doctor) {
        return  ResponseEntity.ok().body(doctorService.saveDoctor(doctor));
    }
}
