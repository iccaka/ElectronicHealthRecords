package com.group12.ElectronicHealthRecords.repositories;

import com.group12.ElectronicHealthRecords.entities.AccessCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessCodeRepository extends JpaRepository<AccessCode, String> {

    @Query("SELECT a FROM AccessCode a WHERE a.used = false")
    List<AccessCode> getValidAccessCodes();
}
