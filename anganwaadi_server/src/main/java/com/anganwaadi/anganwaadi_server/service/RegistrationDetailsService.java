package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.repositories.RegistrationDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class RegistrationDetailsService {
    
    @NonNull
    private final RegistrationDetailsRepository registrationDetRepository;

    @Autowired
    public RegistrationDetailsService(RegistrationDetailsRepository registrationDetRepository){
        this.registrationDetRepository = registrationDetRepository;
    }

    public List<RegistrationDetails> getAllDetails(){
        return registrationDetRepository.findAll();
    } 

    public Optional<RegistrationDetails> getDetailsById(String userId){
        return registrationDetRepository.findById(userId);
    }


    public void saveWorker(RegistrationDetails details){
        registrationDetRepository.save(details);
    }

    public void deleteWorker(String userId){
        registrationDetRepository.deleteById(userId);
    }


}
