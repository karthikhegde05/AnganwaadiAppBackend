package com.anganwaadi.anganwaadi_server.repositories;

import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DischargeSummaryRepository extends JpaRepository<DischargeSummary, Long>{
    List<DischargeSummary> findByPatientOrderByDischargeDateAsc(Patient patient);
}
