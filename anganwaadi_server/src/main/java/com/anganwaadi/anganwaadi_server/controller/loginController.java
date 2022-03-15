package com.anganwaadi.anganwaadi_server.controller;

import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.service.RegistrationDetailsService;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/login",
consumes = MediaType.APPLICATION_JSON_VALUE)
public class loginController {
    
    private RegistrationDetailsService regService;

    public loginController(RegistrationDetailsService regService){
        this.regService = regService;
    }

    @PostMapping
    // public ResponseEntity<String> postLoginCred(@RequestBody String userId){
    //     Optional<RegistrationDetails> regDetails = regService.getDetailsById(userId);
    //     if (regDetails.isPresent()){
    //         return new ResponseEntity<>(HttpStatus.OK);
    //     }

    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    @CrossOrigin("http://localhost:8100")
    public @ResponseBody String checkLogin(@RequestBody DTO dto){

        // String jsonString = request.getBody();
        // System.out.println(jsonString);

        // JSONObject jsonObject = new JSONObject(jsonString);

        System.out.println(dto.getUserID());

        // System.out.println(jsonObject.getString("userID"));
        // Optional<RegistrationDetails> regDetails = regService.getDetailsById(jsonObject.getString("userID"));
        Optional<RegistrationDetails> regDetails = regService.getDetailsById(dto.getUserID());
        if (regDetails.isPresent()){
            return "valid";
        }

        return "invalid";
    }

}

class DTO{

    private String userID;

    public String getUserID(){
        return userID;
    }

    public void setUserID(String id){
        userID = id;
    }
}
