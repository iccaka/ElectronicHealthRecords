package com.group12.ElectronicHealthRecords.beans;

import lombok.Data;

import java.util.Date;

@Data
public class PrescriptionRequest {

    private String medications;
    private Date start_date;
    private Date end_date;
    private String description;
}
