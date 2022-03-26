package com.anganwaadi.anganwaadi_server.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiService;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.FollowUpService;
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

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping(value= "/test")
// consumes = MediaType.APPLICATION_JSON_VALUE)
public class testController {
    
    private FollowUpService followUpService;
    private AnganwaadiWorkerService anganwaadiWorkerService;

    public testController(FollowUpService followUpService, AnganwaadiWorkerService anganwaadiWorkerService){
        this.followUpService = followUpService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
    }

    @PostMapping(value= "/insertfollowup")
    @CrossOrigin("http://localhost:8100")
    public @ResponseBody String insertFollowUp(@RequestBody FollowUpDTO followUp){

        FollowUp f = new FollowUp(
            followUp.getSamId(),
            followUp.getDeadline(),
            followUp.getCompletedOn(),
        null,
            followUp.getCompleted(),
            followUp.getCreatedDate()
            );

        
        Optional<AnganwaadiWorker> a = anganwaadiWorkerService.getWorkerById(followUp.getWorkerId());
        
        if(!a.isPresent()){
            return "false";
        }
        
        AnganwaadiWorker aww = a.get();
        f.setAnganwaadiWorker(aww);

        followUpService.testInsert(f);

        return "true";

    }

    @GetMapping(value = "/sync/{aww_id}/{timestamp}")
    @CrossOrigin("http://localhost:8100")
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

@Data
@NoArgsConstructor
class FollowUpDTO{

    private Long followupId;
    private Long samId;

    private LocalDateTime deadline;
    private LocalDateTime completedOn;

    @Setter @Getter
    private HealthStatus healthStatus;

    private Boolean completed;

    private LocalDateTime createdDate;

    private Long workerId;

    public FollowUpDTO(FollowUp followUp){

        this.followupId = followUp.getFollow_up_id();
        this.samId = followUp.getSAM_ID();
        this.deadline = followUp.getDeadline_date();
        this.completedOn = followUp.getCompleted_date();

        this.healthStatus = followUp.getHealthStatus();

        this.completed = followUp.getCompleted();
        this.createdDate =followUp.getCreatedDate();

        this.workerId = followUp.getAnganwaadiWorker().getAwwId();
    }
}

