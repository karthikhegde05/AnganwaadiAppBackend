package com.anganwaadi.anganwaadi_server.classes;

import java.util.UUID;

public class AnganwaadiWorker {
 
    private String workerId;
    // private List<Patients> lstPatient
    // private String emergencyContact;
    private String username;
    // private String password;

    public AnganwaadiWorker(){
        this.workerId = UUID.randomUUID().toString();
    }

    public String getId(){return workerId;}

    public String getUsername(){return username;}


}
