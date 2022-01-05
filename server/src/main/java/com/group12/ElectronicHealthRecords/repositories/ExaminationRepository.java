package com.group12.ElectronicHealthRecords.repositories;

import com.group12.ElectronicHealthRecords.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    @Query("SELECT e, p FROM Examination e INNER JOIN Prescription p ON e.patient.egn = :patientEgn AND e.prescription.id = p.id")
    List<Examination> findAllPatientExaminations(String patientEgn);
}
