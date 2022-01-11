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
import java.util.Optional;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public ResponseEntity<?> getPrescriptionById(Long id) {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        Map<String, String> response = new HashMap<>();

        if(!prescription.isPresent()) {
            response.put("error_message", "No prescription found with given ID");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok().body(prescription);
    }

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

    public ResponseEntity<?> getPerscriptionById(Long id) {
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        Map<String, String> response = new HashMap<>();

        if(!prescription.isPresent()){
            response.put("error_message", "No examination found with given patient EGN");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok().body(prescription);
    }
}
