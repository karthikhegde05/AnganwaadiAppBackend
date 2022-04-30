package com.anganwaadi.anganwaadi_server.repositories;

import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnganwaadiWorkerRepository extends JpaRepository<AnganwadiWorker, Long>{

    List<AnganwadiWorker> findByRegDetails(RegistrationDetails regDetails);
}
