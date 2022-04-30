package com.anganwaadi.anganwaadi_server.controller;

import java.util.List;
import java.util.Optional;
import java.lang.System;
import java.lang.Long;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.controller.DTO.WorkerDTO;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.RegistrationDetailsService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@RestController
@CrossOrigin("http://localhost:8100")
public class SessionController {
    
    private RegistrationDetailsService regService;
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private Boolean loggedIn;
    private AnganwadiWorker loggedWorker;


    public SessionController(RegistrationDetailsService regService, AnganwaadiWorkerService anganwaadiWorkerService){
        this.regService = regService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.loggedIn = false;
        this.loggedWorker = null;
    }


    public AnganwadiWorker findLoggedAngWorker(RegistrationDetails details){
        List<AnganwadiWorker> allWorkers = anganwaadiWorkerService.getAllWorkers();

        AnganwadiWorker loggedWorker = null;
        for(AnganwadiWorker angWorker: allWorkers){
            if(angWorker.getRegDetails().equals(details)){
                loggedWorker = angWorker;
            }
        }

        return loggedWorker;
    }


    @RequestMapping(value = "/login", method=RequestMethod.POST) // consumes=MEDIATYPE.APPLICATION_JSON_VALUE
    // @CrossOrigin("http://localhost:8100")
    public @ResponseBody SendWorkerIdDTO checkLogin(@RequestBody LoginDTO dto){

        SendWorkerIdDTO sendResult = null;
        Optional<RegistrationDetails> regDetails = regService.getDetailsById(dto.getUserID());
        if (regDetails.isPresent()){
            
            RegistrationDetails details = regDetails.get();

            if( details.getPassword().equals(dto.getPassword()) ){
                this.loggedIn = true;

                // fetch the loggedWorker details and send the aww_id across
                // this.loggedWorker = findLoggedAngWorker(details);

                loggedWorker = anganwaadiWorkerService.getWorkerByReg(details).get(0);

                sendResult = new SendWorkerIdDTO("valid", new WorkerDTO(loggedWorker));
            }
            
        }

        if(sendResult==null){
            sendResult = new SendWorkerIdDTO("invalid", null);
        }


        return sendResult;
    }

}

@Data
@Getter @Setter @AllArgsConstructor
class LoginDTO{

    private String userID;
    private String password;

}

@Data
@Setter @Getter @AllArgsConstructor
class SendWorkerIdDTO{
    private String result;
    // private Long awwId;
    @Setter @Getter
    private WorkerDTO worker;
}
