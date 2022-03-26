package com.anganwaadi.anganwaadi_server.repositories;

import com.anganwaadi.anganwaadi_server.classes.HealthStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthStatusRepository extends JpaRepository<HealthStatus, String>{
    
}
