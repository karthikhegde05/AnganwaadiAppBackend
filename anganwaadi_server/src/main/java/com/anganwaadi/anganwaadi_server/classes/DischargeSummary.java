package com.anganwaadi.anganwaadi_server.classes;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "discharge_summary")
public class DischargeSummary {
    
    @SequenceGenerator(
		name = "discharge_summary_sequence",
		sequenceName = "discharge_summary_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "discharge_summary_sequence"
	)
    @Id
    private Long dischargeId;
    private LocalDate admissionDate;
    private Double admissionWeight;
    private Double targetWeight;
    private Double dischargeWeight;
    private LocalDate dischargeDate;
    private String outcome;
    private String treatmentProtocol;

    private LocalDate createdDate;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "sam_id")
    private Patient patient;

    public DischargeSummary(LocalDate admission, Double adWt, Double tarWt, Double dischargeWeight, LocalDate dischargeDate,
    String outcome, String treatmentProtocol, LocalDate createdDate){
        this.admissionDate = admission;
        this.admissionWeight = adWt;
        this.targetWeight = tarWt;
        this.dischargeWeight = dischargeWeight;
        this.dischargeDate = dischargeDate;
        this.outcome = outcome;
        this.treatmentProtocol = treatmentProtocol;
    }

}