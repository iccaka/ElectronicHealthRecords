package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.entities.AccessCode;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.repositories.AccessCodeRepository;
import com.group12.ElectronicHealthRecords.repositories.DoctorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AdminController {
    private AccessCodeRepository accessCodeRepository;
    private DoctorRepository doctorRepository;

    @PostMapping("/admin/accessCode")
    public ResponseEntity<?> generateAccessCode() {
        Map<String, String> response = new HashMap<>();
        AccessCode accessCode = new AccessCode(UUID.randomUUID().toString(), false);
        accessCodeRepository.save(accessCode);

        response.put("access_code", accessCode.getAccessCode());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/admin/accessCodes")
    public List<AccessCode> getValidAccessCodes() {
        return accessCodeRepository.getValidAccessCodes();
    }

    @PostMapping("/admin/promote/{egn}")
    public ResponseEntity promoteUser(@PathVariable String egn) {
        Map<String, String> response = new HashMap<>();
        Optional<Doctor> doctor = doctorRepository.findById(egn);
        if (doctor.isEmpty()) {
            response.put("message", "No doctor found");
            return ResponseEntity.badRequest().body(response);
        }

        Doctor doc = doctor.get();
        doc.setAdmin(true);
        doctorRepository.save(doc);

        response.put("message", "Doctor promoted to admin");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/demote/{egn}")
    public ResponseEntity demoteUser(@PathVariable String egn) {
        Map<String, String> response = new HashMap<>();
        Optional<Doctor> doctor = doctorRepository.findById(egn);
        if (doctor.isEmpty()) {
            response.put("message", "No doctor found");
            return ResponseEntity.badRequest().body(response);
        }

        Doctor doc = doctor.get();
        doc.setAdmin(false);
        doctorRepository.save(doc);

        response.put("message", "Doctor demoted");
        return ResponseEntity.ok(response);
    }
}
