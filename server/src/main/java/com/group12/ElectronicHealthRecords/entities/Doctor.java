package com.group12.ElectronicHealthRecords.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "egn", nullable = false, length = 10)
    private String egn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Examination> examinations;

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Calendar> calendars;

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public Doctor() {
    }

    public Doctor(String egn, String name, String email, String password, String specialization, List<Examination> examinations) {
        this.egn = egn;
        this.name = name;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
        this.examinations = examinations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }
}
