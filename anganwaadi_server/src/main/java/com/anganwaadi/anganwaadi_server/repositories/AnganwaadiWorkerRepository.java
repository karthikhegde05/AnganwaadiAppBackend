package com.anganwaadi.anganwaadi_server.repositories;

import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnganwaadiWorkerRepository extends JpaRepository<AnganwaadiWorker, Long>{

}
