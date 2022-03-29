package com.anganwaadi.anganwaadi_server.repositories;

import com.anganwaadi.anganwaadi_server.classes.Anganwadi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnganwaadiRepository extends JpaRepository<Anganwadi, Long>{
    
}
