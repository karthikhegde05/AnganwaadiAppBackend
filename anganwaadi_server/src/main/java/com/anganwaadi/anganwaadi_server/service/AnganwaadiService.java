package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.Anganwadi;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class AnganwaadiService {
    
    @NonNull
    private final AnganwaadiRepository anganwaadiRepository;

    @Autowired
    public AnganwaadiService(AnganwaadiRepository anganwaadiRepository){
        this.anganwaadiRepository = anganwaadiRepository;
    }

    public List<Anganwadi> getAllAnganwaadi(){
        return anganwaadiRepository.findAll();
    }

    public Optional<Anganwadi> getAnganwaadiById(Long aw_id){
        return anganwaadiRepository.findById(aw_id);
    }    

    public void saveAnganwaadi(Anganwadi anganwaadi){
        anganwaadiRepository.save(anganwaadi);
    }

    public void deleteAnganwaadi(Long id){
        anganwaadiRepository.deleteById(id);
    }

}
