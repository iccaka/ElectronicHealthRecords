package com.group12.ElectronicHealthRecords.beans;

import lombok.Data;

import java.util.Date;

@Data
public class PrescriptionRequest {

    private String id;
    private String medications;
    private String start_date;
    private String end_date;
    private String description;
}
