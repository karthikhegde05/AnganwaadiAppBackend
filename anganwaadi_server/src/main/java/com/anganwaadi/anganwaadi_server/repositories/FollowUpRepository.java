package com.anganwaadi.anganwaadi_server.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, String>{
    List<FollowUp> findByAnganwaadiWorkerAndCreatedDateGreaterThan(AnganwaadiWorker a, LocalDateTime t);
}
