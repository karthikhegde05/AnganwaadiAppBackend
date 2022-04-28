package com.anganwaadi.anganwaadi_server.repositories;

import java.time.LocalDate;

import com.anganwaadi.anganwaadi_server.classes.HealthStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthStatusRepository extends JpaRepository<HealthStatus, Long>{
    
    @Modifying
    @Query(value=
    "UPDATE health_status h " +
    "SET h.height = :height, h.weight = :weight, " + 
        "h.muac = :muac, h.growth_status = :growthStatus, " + 
        "h.other_symptoms = :otherSymptoms, h.date = :date " +
    "WHERE h.hs_id = :hsId",
    nativeQuery = true)
    int updateHealthStatusById(
        @Param("hsId") Long hsId,
        @Param("height") float height,
        @Param("weight") float weight,
        @Param("muac") float muac,
        @Param("growthStatus") String growthStatus,
        @Param("otherSymptoms") String otherSymptoms,
        @Param("date") LocalDate date

    );
}
