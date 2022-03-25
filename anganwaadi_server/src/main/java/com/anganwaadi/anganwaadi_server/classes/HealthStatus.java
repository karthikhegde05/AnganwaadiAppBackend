package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class HealthStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long HSID;
    private Double height;
    private Double weight;
    private Double MUAC;
    private String growthStatus;
    private String otherSymptoms;

    public HealthStatus(Double height, Double weight, Double MUAC, String growthStatus, String otherSymptoms){
        this.height = height;
        this.weight = weight;
        this.MUAC = MUAC;
        this.growthStatus = growthStatus;
        this.otherSymptoms = otherSymptoms;
    }
}
