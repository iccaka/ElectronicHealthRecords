package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.beans.PrescriptionRequest;
import com.group12.ElectronicHealthRecords.entities.Prescription;
import com.group12.ElectronicHealthRecords.repositories.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public ResponseEntity<?> createPrescription(PrescriptionRequest prescriptionRequest) {
        Map<String, String> response = new HashMap<>();

        Prescription newPrescription = new Prescription();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(prescriptionRequest.getStart_date(), dateTimeFormatter);
        LocalDateTime endDate = LocalDateTime.parse(prescriptionRequest.getEnd_date(), dateTimeFormatter);

        newPrescription.setId(Long.parseLong(prescriptionRequest.getId()));
        newPrescription.setMedications(prescriptionRequest.getMedications());
        newPrescription.setStart_date(startDate);
        newPrescription.setEnd_date(endDate);
        newPrescription.setDescription(prescriptionRequest.getDescription());

        prescriptionRepository.save(newPrescription);

        return ResponseEntity.ok().body(newPrescription);
    }
}
