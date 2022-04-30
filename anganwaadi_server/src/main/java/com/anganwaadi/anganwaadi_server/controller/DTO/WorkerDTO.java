package com.anganwaadi.anganwaadi_server.controller.DTO;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;

import lombok.Data;

@Data
public class WorkerDTO {
    private Long awwId;
    private String name;
    private String contactNo;
    private String address;
    private String locality;

    public WorkerDTO(AnganwadiWorker a){
        this.awwId = a.getAwwId();
        this.name = a.getName();
        this.contactNo = a.getContactNo();
        this.address = a.getAddress();
        this.locality = a.getLocality();
    }
}
