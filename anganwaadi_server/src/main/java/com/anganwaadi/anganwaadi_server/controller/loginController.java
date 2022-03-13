package com.anganwaadi.anganwaadi_server.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    
    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/login/{id}")
    public @ResponseBody String checkCredentials(){

        return "true";
    }

}
