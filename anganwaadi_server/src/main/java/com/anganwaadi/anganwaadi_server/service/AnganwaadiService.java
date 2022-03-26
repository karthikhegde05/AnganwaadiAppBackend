package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.Anganwaadi;
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

    public List<Anganwaadi> getAllAnganwaadi(){
        return anganwaadiRepository.findAll();
    }

    public Optional<Anganwaadi> getAnganwaadiById(Long aw_id){
        return anganwaadiRepository.findById(aw_id);
    }    

    public void saveAnganwaadi(Anganwaadi anganwaadi){
        anganwaadiRepository.save(anganwaadi);
    }

    public void deleteAnganwaadi(Long id){
        anganwaadiRepository.deleteById(id);
    }

}
