package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.repositories.DischargeSummaryRepository;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DischargeSummaryService {
    
    @NonNull
    private final DischargeSummaryRepository disSumRepository;

    public List<DischargeSummary> getAllSummary(){
        return disSumRepository.findAll();
    }

    public Optional<DischargeSummary> getSumaryById(Long id){
        return disSumRepository.findById(id);
    }

    public List<DischargeSummary> getSummaryByPatient(Patient patient){
        return disSumRepository.findByPatientOrderByDischargeDateAsc(patient);
    }

    public void saveSummary(DischargeSummary disSummary){
        disSumRepository.saveAndFlush(disSummary);
    }

    public void deleteSummary(Long id){
        disSumRepository.deleteById(id);
    }

}
