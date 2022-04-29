package com.anganwaadi.anganwaadi_server.controller.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DischargeDTO {
    private Long dischargeId;
    private LocalDate admissionDate;
    private Double admissionWeight;
    private Double targetWeight;
    private Double dischargeWeight;
    private LocalDate dischargeDate;
    private String outcome;
    private String treatmentProtocol;

    private LocalDateTime createdDate;

    public DischargeDTO(DischargeSummary d){
        this.dischargeId = d.getDischargeId();
        this.admissionDate = d.getAdmissionDate();
        this.admissionWeight = d.getAdmissionWeight();
        this.targetWeight = d.getTargetWeight();
        this.dischargeDate = d.getDischargeDate();
        this.outcome = d.getOutcome();
        this.treatmentProtocol = d.getTreatmentProtocol();
        this.createdDate = d.getCreatedDate();
    }
}
