package com.group12.ElectronicHealthRecords.repositories;

import com.group12.ElectronicHealthRecords.entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    @Query(value = "SELECT * FROM examination e INNER JOIN patient p ON p.egn = e.patient_egn INNER JOIN prescription c ON c.id = e.prescription_id WHERE :pegn = p.egn", nativeQuery = true)
    Optional<Examination> findAllPatientExaminations(@Param("pegn") String patientEgn);
}