package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.PatientRequest;
import com.group12.ElectronicHealthRecords.entities.Patient;
import com.group12.ElectronicHealthRecords.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {

    PatientRepository patientRepository;

    @GetMapping("/search/patient/name")
    public ResponseEntity<?> getPatientsByName(@RequestParam String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.ok().body("You have not filled name!");
        }
        Optional<Patient> result = patientRepository.findByName(name);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("There is not found patient!");
    }

    @GetMapping("/search/patient/egn")
    public ResponseEntity<?> getPatientsByEgn(@RequestParam String egn) {
        if (egn == null || egn.isBlank()) {
            return ResponseEntity.ok().body("You have not filled egn!");
        }
        Optional<Patient> result = patientRepository.findByEgn(egn);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("There is not found egn!");
    }

    @PostMapping("/patient/save")
    public ResponseEntity<?> savePatient(@RequestBody PatientRequest patientRequest) {
        Map<String, String> response = new HashMap<>();
        Optional<Patient> patient = patientRepository.findByEgn(patientRequest.getEgn());
        if (patient.isPresent()) {
            response.put("error_message", "Patient with this egn already exists!");
            return ResponseEntity.badRequest().body(response);
        }
        Patient newPatient = new Patient(
                patientRequest.getEgn(),
                patientRequest.getName(),
                patientRequest.getEmail(),
                patientRequest.getAllergies(),
                patientRequest.getImmunizationStatute(),
                patientRequest.getBloodType(),
                patientRequest.getWeight(),
                patientRequest.getDateOfBirth(),
                patientRequest.getIllness(),
                new ArrayList<>(),
                new ArrayList<>());
        return  ResponseEntity.ok().body(patientRepository.save(newPatient));
    }

    @PutMapping("/patient/update")
    public ResponseEntity<?> updatePatient(@RequestBody PatientRequest patientRequest) {
        Map<String, String> response = new HashMap<>();
        Optional<Patient> patient = patientRepository.findByEgn(patientRequest.getEgn());
        if (!patient.isPresent()) {
            response.put("error_message", "Patient with this egn does not exist!");
            return ResponseEntity.badRequest().body(response);
        }
        Patient newPatient = new Patient(
                patientRequest.getEgn(),
                patientRequest.getName(),
                patientRequest.getEmail(),
                patientRequest.getAllergies(),
                patientRequest.getImmunizationStatute(),
                patientRequest.getBloodType(),
                patientRequest.getWeight(),
                patientRequest.getDateOfBirth(),
                patientRequest.getIllness(),
                patient.get().getExaminations(),
                patient.get().getCalendars()
        );
        return  ResponseEntity.ok().body(patientRepository.save(newPatient));
    }
}
