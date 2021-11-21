package com.group12.ElectronicHealthRecords.services;

import lombok.RequiredArgsConstructor;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.repositories.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImplementation implements DoctorService, UserDetailsService {

    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(email);
        if(doctor == null){
            log.error("Doctor not found in the database!");
            throw new UsernameNotFoundException("Doctor not found in the database!");
        } else {
            log.info("Doctor found in the database: {}", email);
        }
        return  new org.springframework.security.core.userdetails.User();
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        log.info("Saving new doctor to the database.");
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(String email) {
        log.info("Fetching doctor {}.", email);
        return doctorRepository.findByEmail(email);
    }

    @Override
    public List<Doctor> getDoctors() {
        log.info("Fetching all doctors.");
        return doctorRepository.findAll();
    }
}
