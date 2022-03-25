package com.anganwaadi.anganwaadi_server.service;

import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.repositories.PatientRepository;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    
    @NonNull
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long samId){
        return patientRepository.findById(samId);
    }

    public void savePatient(Patient patient){
        patientRepository.saveAndFlush(patient);
    }

    public void deletePatient(Long samId){
        patientRepository.deleteById(samId);
    }

}
