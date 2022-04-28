package com.anganwaadi.anganwaadi_server.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.repositories.PatientRepository;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiService;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.FollowUpService;
import com.anganwaadi.anganwaadi_server.service.HealthStatusService;
import com.anganwaadi.anganwaadi_server.service.PatientService;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.anganwaadi.anganwaadi_server.controller.DTO.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/test")
// consumes = MediaType.APPLICATION_JSON_VALUE)
public class testController {
    
    private FollowUpService followUpService;
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private PatientService patientService;
    private HealthStatusService healthStatusService;

    public testController(FollowUpService followUpService, 
        AnganwaadiWorkerService anganwaadiWorkerService,
        PatientService patientService,
        HealthStatusService healthStatusService){
        this.followUpService = followUpService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.patientService = patientService;
        this.healthStatusService = healthStatusService;
    }

    @PostMapping(value= "/insertfollowup")
    // @CrossOrigin("http://localhost:8100")
    public @ResponseBody String insertFollowUp(@RequestBody FollowUpDTO followUpDto){

        HealthStatus healthStatus = new HealthStatus();

        Patient patient = patientService.getPatientById(followUpDto.getSamId()).get();

        FollowUp followUp = new FollowUp(patient,
            followUpDto.getCreatedDate(), 
            followUpDto.getCompleted_date(), 
            healthStatus,
            followUpDto.getCompleted(), 
            followUpDto.getCreatedDate());

        
        Optional<AnganwadiWorker> a = anganwaadiWorkerService.getWorkerById(followUpDto.getWorkerId());
        
        if(!a.isPresent()){
            return "false";
        }
        
        AnganwadiWorker aww = a.get();
        followUp.setAnganwaadiWorker(aww);

        healthStatus.setPatient(patient);
        healthStatusService.saveHealthStatus(healthStatus);
        followUpService.testInsert(followUp);

        return "true";

    }

    @PostMapping(value="/insertpatient")
    public @ResponseBody String insertPatient(@RequestBody PatientDTO patientDto){

        Patient patient = new Patient(
            patientDto.getUhId(),
            patientDto.getRchId(),
            patientDto.getName(),
            patientDto.getAge(),
            patientDto.getDob(),
            patientDto.getGender(),
            patientDto.getAddress(),
            patientDto.getCity(),
            patientDto.getContactNumber(),
            patientDto.getRelationshipStatus(),
            patientDto.getCaste(),
            patientDto.getReligion(),
            patientDto.getBpl(), 
            patientDto.getReferredBy()
        );

        patient.setLastUpdated(LocalDateTime.now());

        patientService.savePatient(patient);

        return "success";

    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/sync/{aww_id}/{timestamp}")
    public @ResponseBody List<FollowUpDTO> syncFollowUp(@PathVariable("aww_id") Long aww_id, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp){

        System.out.println(aww_id);
        System.out.println(timestamp);

        
        List<FollowUp> a = followUpService.sync(anganwaadiWorkerService.getWorkerById(aww_id).get(), timestamp);
        System.out.println(a.size());

        ArrayList<FollowUpDTO> b = new ArrayList<>();

        for(FollowUp i : a){
            b.add(new FollowUpDTO(i));
        }

        return b;

        // return a;
    }

}







