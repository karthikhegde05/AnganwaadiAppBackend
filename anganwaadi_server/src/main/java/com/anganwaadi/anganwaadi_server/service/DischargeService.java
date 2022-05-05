package com.anganwaadi.anganwaadi_server.service;

// import java.time.LocalDateTime;
// import java.util.List;

// import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
// import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.repositories.DischargeRepository;
// import com.anganwaadi.anganwaadi_server.repositories.FollowUpRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class DischargeService {
    
    @NonNull
    private final DischargeRepository dischargeRepository;

    @Autowired
    public DischargeService(DischargeRepository dischargeRepository){
        this.dischargeRepository = dischargeRepository;
    }

    public DischargeSummary getLatestDischarge(Patient p){
        return this.dischargeRepository.findTop1ByPatientOrderByCreatedDateDesc(p);
    } 

}
