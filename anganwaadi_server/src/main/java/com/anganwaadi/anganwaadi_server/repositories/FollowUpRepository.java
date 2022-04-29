package com.anganwaadi.anganwaadi_server.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, String>{
    List<FollowUp> findByAnganwaadiWorkerAndCreatedDateGreaterThan(AnganwadiWorker a, LocalDateTime t);
    List<FollowUp> findByPatientAndCreatedDateGreaterThanAndCompletedIsTrue(Patient p, LocalDateTime t);

    @Modifying
    @Query(value=
    "UPDATE follow_up f " +
    "SET f.completed = true, f.completed_date = :date, f.created_date = :datetime " + 
    "WHERE f.followup_id = :Id",
    nativeQuery = true)
    int updateFollowupById(
        @Param("Id") Long hsId,
        @Param("date") LocalDate date,
        @Param("datetime") LocalDateTime dateTime
    );
}
