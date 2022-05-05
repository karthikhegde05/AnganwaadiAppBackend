package com.anganwaadi.anganwaadi_server.classes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
// import java.util.Date;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Patient {
    
    @SequenceGenerator(
		name = "patient_sequence",
		sequenceName = "patient_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "patient_sequence"
	)
    @Id
    private Long samId;

    private String uhId;
    private String rchId;
    private String name;
    private Integer age;
    private LocalDate dob;
    private Character gender;
    private String address;
    private String city;
    private String contactNumber;
    private String relationshipStatus;
    private String caste;
    private String religion;
    private Boolean BPL;
    private String referredBy;
    private String symptoms;

    // link one to many health status while admision
    // link one to many discharge summary while discharges
    // link one to many followup : Doubtful

    @OneToMany(mappedBy = "patient")
    private List<HealthStatus> healthStatus;

    @OneToMany(mappedBy = "patient")
    private List<FollowUp> followups;

    @OneToMany(mappedBy = "patient")
    private List<DischargeSummary> dischargeSummaries; 

    private LocalDateTime lastUpdated;

    public Patient(String uhid, String Rchid, String name, Integer age, LocalDate dob, Character gender,
    String address, String city, String contactNumber, String relStatus, String caste, 
    String religion, Boolean bpl, String referredBy){
        this.uhId = uhid;
        this.rchId = Rchid;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.contactNumber = contactNumber;
        this.relationshipStatus = relStatus;
        this.caste = caste;
        this.religion = religion;
        this.BPL = bpl;
        this.referredBy = referredBy;
    }


}
