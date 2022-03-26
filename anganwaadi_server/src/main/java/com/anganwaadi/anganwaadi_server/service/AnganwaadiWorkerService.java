package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiWorkerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.NonNull;
// import lombok.RequiredArgsConstructor;


@Service
// @RequiredArgsConstructor
public class AnganwaadiWorkerService {

    @NonNull
    private final AnganwaadiWorkerRepository anganwaadiWorkerRepository;

    @Autowired
    AnganwaadiWorkerService(AnganwaadiWorkerRepository anganwaadiWorkerRepository){
        this.anganwaadiWorkerRepository = anganwaadiWorkerRepository;
    }

    public List<AnganwaadiWorker> getAllWorkers(){
        return anganwaadiWorkerRepository.findAll();
    }

    public Optional<AnganwaadiWorker> getWorkerById(Long aww_id){
        return anganwaadiWorkerRepository.findById(aww_id);
    }    

    public void saveWorker(AnganwaadiWorker worker){
        anganwaadiWorkerRepository.save(worker);
    }

    public void deleteWorker(Long id){
        anganwaadiWorkerRepository.deleteById(id);
    }

}
