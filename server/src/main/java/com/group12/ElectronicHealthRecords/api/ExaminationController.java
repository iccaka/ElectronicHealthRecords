package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.entities.Examination;
import com.group12.ElectronicHealthRecords.entities.Patient;
import com.group12.ElectronicHealthRecords.repositories.ExaminationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExaminationController {

    ExaminationRepository examinationRepository;

    @GetMapping("/search/examination")
    public ResponseEntity<?> getPatientExaminationByEgn(@RequestParam String patientEgn) {
        if (patientEgn == null || patientEgn.isBlank()) {
            return ResponseEntity.ok().body("Въвели сте грешно егн!");
        }
        List<Examination> result = examinationRepository.findPatientExaminationByEgn(patientEgn.toLowerCase());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save/patientExamination")
    public ResponseEntity<?> savePatientExamination(@RequestBody Examination examination) {
        examination = examinationRepository.save(examination);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Успешно записано изследване!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/update/patientExamination")
    public ResponseEntity<?> updatePatientExamination(@RequestBody Examination examination){
        examination = examinationRepository.save(examination);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Успешно редактирано изследване!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
