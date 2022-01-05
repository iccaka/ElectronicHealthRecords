package com.group12.ElectronicHealthRecords.repositories;

import com.group12.ElectronicHealthRecords.entities.Prescription;
import org.apache.coyote.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    Optional<Prescription> findById(Long id);
}
