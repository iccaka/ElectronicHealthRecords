package com.group12.ElectronicHealthRecords.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "medications")
    private String medications;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

    @Column(name = "description")
    private String description;

    public Prescription(String medications, LocalDateTime start_date, LocalDateTime end_date, String description) {
        this.medications = medications;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
    }
}
