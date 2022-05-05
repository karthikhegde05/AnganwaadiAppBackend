package com.anganwaadi.anganwaadi_server.classes;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// import org.hibernate.annotations.Fetch;
// import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
@Table(name = "health_status")
public class HealthStatus {
    
    @SequenceGenerator(
		name = "healthstatus_sequence",
		sequenceName = "healthstatus_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "healthstatus_sequence"
	)
    @Id
    private Long hsId;

    private float height;
    private float weight;
    private float muac;
    private String growthStatus;
    private String otherSymptoms;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sam_id")
    private Patient patient;

    public HealthStatus(Float height, Float weight, Float muac, String growthStatus, String otherSymptoms){
        this.height = height;
        this.weight = weight;
        this.muac = muac;
        this.growthStatus = growthStatus;
        this.otherSymptoms = otherSymptoms;
    }

}
