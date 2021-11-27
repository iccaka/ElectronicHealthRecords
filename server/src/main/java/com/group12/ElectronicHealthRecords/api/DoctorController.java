package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.DoctorRequest;
import com.group12.ElectronicHealthRecords.entities.AccessCode;
import com.group12.ElectronicHealthRecords.repositories.AccessCodeRepository;
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
    private final AccessCodeRepository accessCodeRepository;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>>getDoctors() {
        return  ResponseEntity.ok().body(doctorService.getDoctors());
    }

    @PostMapping("/doctor/save")
    public ResponseEntity<?>saveDoctor(@RequestBody DoctorRequest doctorRequest) {

        AccessCode accessCode = accessCodeRepository.findByAccessCode(doctorRequest.getAccessCode());
        if(accessCode == null || accessCode.getUsed()){
            return ResponseEntity.badRequest().body("Invalid access code!");
        }
        accessCode.setUsed(true);

        Doctor doctor = new Doctor(doctorRequest.getEgn(), doctorRequest.getName(),
                        doctorRequest.getEmail(), doctorRequest.getPassword(),
                        doctorRequest.getSpecialization(), null, null);
        doctorRequest.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
        return  ResponseEntity.ok().body(doctorService.saveDoctor(doctor));
    }
}
