package com.group12.ElectronicHealthRecords.beans;
import com.group12.ElectronicHealthRecords.entities.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationRequest {

    private Long id;
    private String results;
    private Date date;
    private String patientEgn;
    private Long prescription_id;
}
