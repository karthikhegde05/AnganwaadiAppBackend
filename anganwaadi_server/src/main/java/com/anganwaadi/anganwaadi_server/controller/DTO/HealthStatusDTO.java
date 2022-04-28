package com.anganwaadi.anganwaadi_server.controller.DTO;

import java.time.LocalDate;

import com.anganwaadi.anganwaadi_server.classes.HealthStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public
class HealthStatusDTO{

    private Long hsId;
    private float height;
    private float weight;
    private float muac;
    private String growthStatus;
    private String otherSymptoms;
    private LocalDate date;
    private Long sam_id;

    public HealthStatusDTO(HealthStatus h){
        this.hsId = h.getHsId();
        this.height = h.getHeight();
        this.weight = h.getWeight();
        this.muac = h.getMuac();
        this.growthStatus = h.getGrowthStatus();
        this.otherSymptoms = h.getOtherSymptoms();
        this.date = h.getDate();
        this.sam_id = null;
    }

    // public HealthStatusDTO(Long hsId, float height, float weight, float muac, String growth)

}
