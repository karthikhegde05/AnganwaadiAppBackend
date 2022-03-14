package com.anganwaadi.anganwaadi_server.repositories;

import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDetailsRepository extends JpaRepository<RegistrationDetails, String>{
    
}
