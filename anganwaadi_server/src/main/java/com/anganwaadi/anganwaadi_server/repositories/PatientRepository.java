package com.anganwaadi.anganwaadi_server.repositories;



import java.time.LocalDateTime;

import com.anganwaadi.anganwaadi_server.classes.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}