package com.anganwaadi.anganwaadi_server.classes;

import java.sql.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class DischargeSummary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dischargeId;
    private Date admissionDate;
    private Double admissionWt;
    private Double targetWt;
    private Date dischargeDate;
    private String outcome;
    // private String treatmentProtocol;

    @ManyToOne(targetEntity = Patient.class)
    private Patient patient;

    public DischargeSummary(Date admission, Double adWt, Double tarWt, Date dischargeDate,
    String outcome){
        this.admissionDate = admission;
        this.admissionWt = adWt;
        this.targetWt = tarWt;
        this.dischargeDate = dischargeDate;
        this.outcome = outcome;
    }

}
