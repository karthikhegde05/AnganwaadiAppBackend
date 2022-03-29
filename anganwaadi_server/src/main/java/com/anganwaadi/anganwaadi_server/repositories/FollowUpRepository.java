package com.anganwaadi.anganwaadi_server.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, String>{
    List<FollowUp> findByAnganwaadiWorkerAndCreatedDateGreaterThan(AnganwadiWorker a, LocalDateTime t);
}
