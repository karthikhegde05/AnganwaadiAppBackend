package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.repositories.HealthStatusRepository;

import org.springframework.stereotype.Service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthStatusService {
    @NonNull
    private final HealthStatusRepository hsRepository;

    public List<HealthStatus> getAllHealthStatus(){
        return hsRepository.findAll();
    }

    public Optional<HealthStatus> getHealthStatusById(Long id){
        return hsRepository.findById(id);
    }

    public void saveHealthStatus(HealthStatus hs){
        hsRepository.saveAndFlush(hs);
    }

    public void deleteHealthStatus(Long id){
        hsRepository.deleteById(id);
    }
}
