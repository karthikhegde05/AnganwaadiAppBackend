package com.anganwaadi.anganwaadi_server.repositories;

import com.anganwaadi.anganwaadi_server.classes.Anganwaadi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnganwaadiRepository extends JpaRepository<Anganwaadi, Long>{
    
}
