package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.PrescriptionRequest;
import com.group12.ElectronicHealthRecords.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping("/prescription/save")
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        return prescriptionService.createPrescription(prescriptionRequest);
    }
}
