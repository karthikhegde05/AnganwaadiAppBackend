package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiWorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.NonNull;

@Service
public class AnganwaadiWorkerService {

    @NonNull
    private final AnganwaadiWorkerRepository anganwaadiWorkerRepository;

    @Autowired
    AnganwaadiWorkerService(AnganwaadiWorkerRepository anganwaadiWorkerRepository){
        this.anganwaadiWorkerRepository = anganwaadiWorkerRepository;
    }

    public List<AnganwadiWorker> getAllWorkers(){
        return anganwaadiWorkerRepository.findAll();
    }

    public Optional<AnganwadiWorker> getWorkerById(Long aww_id){
        return anganwaadiWorkerRepository.findById(aww_id);
    }    

    public void saveWorker(AnganwadiWorker worker){
        anganwaadiWorkerRepository.save(worker);
    }

    public void deleteWorker(Long id){
        anganwaadiWorkerRepository.deleteById(id);
    }

}
