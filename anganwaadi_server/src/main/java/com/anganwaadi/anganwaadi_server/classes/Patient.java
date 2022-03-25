package com.anganwaadi.anganwaadi_server.classes;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SAMID;

    private String UHID;
    private String RCHID;
    private String name;
    private String age;
    private Date dob;
    private String gender;
    private String address;
    private String city;
    private String contactNumber;
    private String relationshipStatus;
    private String caste;
    private String religion;
    private String BPL;
    private String referredBy;

    // link one to many health status while admision
    // link one to many discharge summary while discharges
    // link one to many followup : Doubtful

    @OneToMany(targetEntity = HealthStatus.class)
    private List<HealthStatus> admissionHS;

    public Patient(String uhid, String Rchid, String name, String age, Date dob, String gender,
    String address, String city, String contactNumber, String relStatus, String caste, 
    String religion, String bpl, String referredBy){
        this.UHID = uhid;
        this.RCHID = Rchid;
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
