package com.anganwaadi.anganwaadi_server.controller;

import java.util.List;
import java.util.Map;

import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.FollowUpService;
import com.anganwaadi.anganwaadi_server.service.HealthStatusService;
import com.anganwaadi.anganwaadi_server.service.PatientService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anganwaadi.anganwaadi_server.controller.DTO.*;


@RestController
@RequestMapping(value= "/followup")
public class FollowupController {
   
    private FollowUpService followUpService;
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private PatientService patientService;
    private HealthStatusService healthStatusService;


    public FollowupController(FollowUpService followUpService, 
        AnganwaadiWorkerService anganwaadiWorkerService,
        PatientService patientService,
        HealthStatusService healthStatusService){
        this.followUpService = followUpService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.patientService = patientService;
        this.healthStatusService = healthStatusService;
    }

    @PostMapping(value = "/update")
    public @ResponseBody String updateFollowups(@RequestBody Map<String,HealthStatusDTO> followups){

        // System.out.println(followups.get("0").getMuac());

        for(Map.Entry<String, HealthStatusDTO> followup : followups.entrySet()){


            Long id;
            try{
                id = Long.parseLong(followup.getKey());
            }
            catch(Exception e){
                continue;
            }

            HealthStatusDTO h = followup.getValue();
            healthStatusService.updateHealthStatus(h.getHsId(), h.getHeight(), h.getWeight(), h.getMuac(), h.getGrowthStatus(), h.getOtherSymptoms(), h.getDate());
            followUpService.updateFollowup(id, h.getDate());

        }

        return "success";
    }


}

    
    

    