package com.anganwaadi.anganwaadi_server.controller;

import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.service.RegistrationDetailsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class loginController {
    
    private RegistrationDetailsService regService;

    public loginController(RegistrationDetailsService regService){
        this.regService = regService;
    }

    @PostMapping
    public ResponseEntity<String> postLoginCred(@RequestBody String userId){
        Optional<RegistrationDetails> regDetails = regService.getDetailsById(userId);
        if (regDetails.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
