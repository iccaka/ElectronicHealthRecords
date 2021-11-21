package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.entities.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    Doctor getDoctor(String email);
    List<Doctor> getDoctors();
}
