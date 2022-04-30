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
import com.anganwaadi.anganwaadi_server.service.DischargeService;
import com.anganwaadi.anganwaadi_server.service.FollowUpService;
import com.anganwaadi.anganwaadi_server.service.HealthStatusService;
import com.anganwaadi.anganwaadi_server.service.PatientService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.anganwaadi.anganwaadi_server.controller.DTO.*;


@RestController
@RequestMapping(value= "/sync")
public class SyncController {
    
    private FollowUpService followUpService;
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private PatientService patientService;
    private HealthStatusService healthStatusService;
    private DischargeService dischargeService;

    public SyncController(FollowUpService followUpService, 
        AnganwaadiWorkerService anganwaadiWorkerService,
        PatientService patientService,
        HealthStatusService healthStatusService,
        DischargeService dischargeService){
        this.followUpService = followUpService;
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.patientService = patientService;
        this.healthStatusService = healthStatusService;
        this.dischargeService = dischargeService;
    }

    @GetMapping(value = "/followup/{aww_id}/{timestamp}")
    @CrossOrigin("http://localhost:8100")
    public @ResponseBody List<FollowUpDTO> syncFollowUp(@PathVariable("aww_id") Long aww_id, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp,
    @RequestHeader MultiValueMap<String, String> headers) {
        
        headers.forEach((key, value) -> {
            // LOG.info(String.format(
            //   "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
            System.out.println(key + " : " + value);
        });

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

        System.out.println(followupdtos);

        return followupdtos;

        
    }

    @PostMapping(value="/patient/")
    public @ResponseBody List<PatientDTO>  syncPatients(@RequestBody List<SyncRequest> requests) {
        
        ArrayList<PatientDTO> updates = new ArrayList<>();


        for(SyncRequest request: requests){
            
            Patient patient = patientService.getIfUpdated(request.getId(), request.getLastUpdate());

            if(patient != null){
                PatientDTO dto = new PatientDTO(patient);
                ArrayList<FollowUpDTO> followupdtos = new ArrayList<>();


                for(FollowUp followUp: followUpService.getNewFollowUps(patient, request.getLastUpdate())){
                    followupdtos.add(new FollowUpDTO(followUp));
                }

                dto.setFollowups(followupdtos);
                DischargeSummary d = dischargeService.getLatestDischarge(patient);
                dto.setDischargeSummary(new DischargeDTO(d));

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

// @Data
// class ListSyncRequest{

//     private ArrayList<Long> ids;
//     private ArrayList<LocalDateTime> lastUpdate;
// }

@Data
class SyncRequest{

    private Long id;
    private LocalDateTime lastUpdate;

}