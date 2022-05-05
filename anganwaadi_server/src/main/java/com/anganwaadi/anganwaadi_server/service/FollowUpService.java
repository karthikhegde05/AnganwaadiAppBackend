package com.anganwaadi.anganwaadi_server.service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.repositories.FollowUpRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class FollowUpService {
    
    @NonNull
    private final FollowUpRepository followUpRepository;

    @Autowired
    public FollowUpService(FollowUpRepository followUpRepository){
        this.followUpRepository = followUpRepository;
    }

    public void testInsert(FollowUp followUp){
        followUpRepository.save(followUp);
    }

    
    public List<FollowUp> sync(AnganwadiWorker id, LocalDateTime t){
        return followUpRepository.findByAnganwaadiWorkerAndCreatedDateGreaterThan(id, t);
    }

    public List<FollowUp> getNewFollowUps(Patient p, LocalDateTime t){
        return followUpRepository.findByPatientAndCreatedDateGreaterThanAndCompletedIsTrue(p, t);
    }

    @Transactional
    public void updateFollowup(Long id ,LocalDate CompletedDate){
        followUpRepository.updateFollowupById(id, CompletedDate, LocalDateTime.now());
    }
}
