package com.anganwaadi.anganwaadi_server.controller.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Patient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public
class PatientDTO{

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
    private Boolean bpl;
    private String referredBy;
    private LocalDateTime last_updated;
    
    private List<DischargeSummary> dischargeSummaries;
    private List<FollowUpDTO> followups;

    public PatientDTO(Patient patient){
        this.samId = patient.getSamId();
        this.uhId = patient.getUhId();
        this.rchId = patient.getRchId();
        this.name = patient.getName();
        this.age = patient.getAge();
        this.dob = patient.getDob();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.city = patient.getCity();
        this.contactNumber = patient.getContactNumber();
        this.relationshipStatus = patient.getRelationshipStatus();
        this.caste = patient.getCaste();
        this.religion = patient.getReligion();
        this.bpl = patient.getBPL();
        this.referredBy = patient.getReferredBy();
        this.last_updated = patient.getLastUpdated();

        this.dischargeSummaries = new ArrayList<>();
        this.followups = new ArrayList<>();
    }

}
