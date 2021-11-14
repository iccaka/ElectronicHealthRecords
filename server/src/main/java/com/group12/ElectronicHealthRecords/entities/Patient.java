package com.group12.ElectronicHealthRecords.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
public class Patient {

    @Id
    @Column(name = "egn", nullable = false, length = 10)
    private String egn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "allergies", nullable = false)
    private String allergies;

    @Column(name = "immunizationStatute", nullable = false)
    private String immunizationStatute;

    @Column(name = "bloodType", nullable = false)
    private Character bloodType;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "laboratoryResult")
    private String laboratoryResult;

    @Column(name = "illness")
    private String illness;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private List<Examination> examinations;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private List<Calendar> calendars;

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public Patient() {
    }

    public Patient(String name, String egn, String email, String allergies, String immunizationStatute, Character bloodType,
                    Integer weight, Date dateOfBirth) {
        this.name = name;
        this.egn = egn;
        this.email = email;
        this.allergies = allergies;
        this.immunizationStatute = immunizationStatute;
        this.bloodType = bloodType;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getImmunizationStatute() {
        return immunizationStatute;
    }

    public void setImmunizationStatute(String immunizationStatute) {
        this.immunizationStatute = immunizationStatute;
    }

    public Character getBloodType() {
        return bloodType;
    }

    public void setBloodType(Character bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLaboratoryResult() {
        return laboratoryResult;
    }

    public void setLaboratoryResult(String laboratoryResult) {
        this.laboratoryResult = laboratoryResult;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }
}

