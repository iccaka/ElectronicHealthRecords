package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.entities.Patient;
import com.group12.ElectronicHealthRecords.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {
    PatientRepository patientRepository;

    @GetMapping("/search/patient")
    public ResponseEntity<?> getPatientsByName(@RequestParam String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.ok().body("Въвели сте грешно име!");
        }
        Optional<Patient> result = patientRepository.findByName(name.toLowerCase());
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("Няма намерен пациент!");
    }

    @GetMapping("/search/patient/egn")
    public ResponseEntity<?> getPatientsByEgn(@RequestParam String egn) {
        if (egn == null || egn.isBlank()) {
            return ResponseEntity.ok().body("Въвели сте грешно егн!");
        }
        Optional<Patient> result = patientRepository.findByEgn(egn);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("Няма намерено егн!");
    }

    @PostMapping("/save/patient")
    public ResponseEntity<?> savePatient(@RequestBody Patient patient) {
        patient = patientRepository.save(patient);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Успешно записан пациент!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
