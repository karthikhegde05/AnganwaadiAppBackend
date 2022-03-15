package com.anganwaadi.anganwaadi_server.controller;

import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.service.RegistrationDetailsService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;



@RestController
@RequestMapping(value = "/login",
consumes = MediaType.APPLICATION_JSON_VALUE)
public class loginController {
    
    private RegistrationDetailsService regService;

    public loginController(RegistrationDetailsService regService){
        this.regService = regService;
    }

    @PostMapping
    @CrossOrigin("http://localhost:8100")
    public @ResponseBody String checkLogin(@RequestBody LoginDTO dto){

        System.out.println(dto.getUserID());

        Optional<RegistrationDetails> regDetails = regService.getDetailsById(dto.getUserID());
        if (regDetails.isPresent()){
            
            RegistrationDetails details = regDetails.get();

            if( details.getPassword().equals(dto.getPassword()) ) 
                return "valid";
            else
                return "invalid";

        }

        return "invalid";
    }

}

@Data
class LoginDTO{

    private String userID;
    private String password;
}
