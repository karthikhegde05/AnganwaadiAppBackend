package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor
public class HealthStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String HS_ID;

    private float height;
    private float weight;
    private float muac;
    private String growth_status;
    private String symptoms;

}
