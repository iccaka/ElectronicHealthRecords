package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.PrescriptionRequest;
import com.group12.ElectronicHealthRecords.services.PrescriptionService;
import com.group12.ElectronicHealthRecords.entities.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/prescription")
    public ResponseEntity<?> getPrescriptionById(@RequestParam Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping("/prescription/save")
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        return prescriptionService.createPrescription(prescriptionRequest);
    }
}