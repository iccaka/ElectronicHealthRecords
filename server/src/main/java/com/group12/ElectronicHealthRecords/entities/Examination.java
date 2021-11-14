package com.group12.ElectronicHealthRecords.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examination")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "results", nullable = false)
    private String results;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "doctor_egn")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_egn")
    private Patient patient;

    public Examination() {
    }

    public Examination(Long id, String results, Date date, Doctor doctor, Patient patient) {
        this.id = id;
        this.results = results;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
