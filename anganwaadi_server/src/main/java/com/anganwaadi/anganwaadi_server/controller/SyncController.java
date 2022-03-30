package com.anganwaadi.anganwaadi_server.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.FollowUp;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.FollowUpService;
import com.anganwaadi.anganwaadi_server.service.HealthStatusService;
import com.anganwaadi.anganwaadi_server.service.PatientService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value= "/sync")
public class SyncController {
    
    private FollowUpService followUpService;
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private PatientService patientService;
    private HealthStatusService healthStatusService;

    public SyncController(FollowUpService followUpService, 
        AnganwaadiWorkerService anganwaadiWorkerService,
        PatientService patientService,
        HealthStatusService healthStatusService){
        this.followUpService = followUpService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.patientService = patientService;
        this.healthStatusService = healthStatusService;
    }

    @GetMapping(value = "/followup/{aww_id}/{timestamp}")
    @CrossOrigin("http://localhost:8100")
    public @ResponseBody List<FollowUpDTO> syncFollowUp(@PathVariable("aww_id") Long aww_id, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp){

        System.out.println(aww_id);
        System.out.println(timestamp);


        Optional<AnganwadiWorker> aww = anganwaadiWorkerService.getWorkerById(aww_id);

        if(aww.isEmpty()){
            return null;
        }
        
        List<FollowUp> followups = followUpService.sync(aww.get(), timestamp);
        // System.out.println(a.size());

        ArrayList<FollowUpDTO> followupdtos = new ArrayList<>();

        for(FollowUp i : followups){
            followupdtos.add(new FollowUpDTO(i));
        }

        return followupdtos;

        
    }

    @PostMapping(value="/patient/")
    public @ResponseBody List<PatientDTO>  postMethodName(@RequestBody ListSyncRequest requests) {
        
        ArrayList<PatientDTO> updates = new ArrayList<>();

        ArrayList<Long> samId = requests.getIds();
        ArrayList<LocalDateTime> lastUpdate = requests.getLastUpdate();

        if(samId.size() != lastUpdate.size()){
            return null;
        }

        for(int i = 0; i < samId.size(); i++){

            Patient patient = patientService.getIfUpdated(samId.get(i), lastUpdate.get(i));
            
            if(patient != null){
                
                PatientDTO dto = new PatientDTO(patient);
                ArrayList<FollowUpDTO> followupdtos = new ArrayList<>();

                for(FollowUp followUp: followUpService.getNewFollowUps(patient, lastUpdate.get(i))){
                    followupdtos.add(new FollowUpDTO(followUp));
                }

                dto.setFollowups(followupdtos);
                updates.add(dto);

            }

            

        }

        return updates;
    }
    
    

}

// @Data
// class PatientSyncDTO{

//     private String sam_id;
//     private String uhId;
//     private String rchId;
//     private String name;
//     private Integer age;
//     private LocalDate dob;
//     private String gender;
//     private String address;
//     private String city;
//     private String contactNumber;
//     private String relationshipStatus;
//     private String caste;
//     private String religion;
//     private Boolean bpl;
//     private String referredBy;
    
//     private List<DischargeSummary> dischargeSummaries;
//     private List<FollowUpDTO> followups;

// }

@Data
class ListSyncRequest{

    private ArrayList<Long> ids;
    private ArrayList<LocalDateTime> lastUpdate;
}