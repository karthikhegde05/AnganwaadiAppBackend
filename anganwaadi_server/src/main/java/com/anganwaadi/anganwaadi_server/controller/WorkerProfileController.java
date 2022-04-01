package com.anganwaadi.anganwaadi_server.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Followup;
import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.service.AnganwaadiWorkerService;
import com.anganwaadi.anganwaadi_server.service.DischargeSummaryService;
import com.anganwaadi.anganwaadi_server.service.FollowupService;
import com.anganwaadi.anganwaadi_server.service.HealthStatusService;
import com.anganwaadi.anganwaadi_server.service.PatientService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
@CrossOrigin("http://localhost:8100")
public class WorkerProfileController {
    private AnganwaadiWorkerService anganwaadiWorkerService;
    private DischargeSummaryService disSumService;
    private FollowupService followupService;
    private PatientService patientService;
    private HealthStatusService healthStatService;
    private AnganwaadiWorker loggedWorker;

    public WorkerProfileController(AnganwaadiWorkerService anganwaadiWorkerService, DischargeSummaryService disSumService,
        FollowupService followupService, PatientService patientService, HealthStatusService healthStatService){
        this.anganwaadiWorkerService = anganwaadiWorkerService;
        this.disSumService = disSumService;
        this.followupService = followupService;
        this.patientService = patientService;
        this.healthStatService = healthStatService;
        this.loggedWorker = null;
    }


    @RequestMapping(value="/workerProfile/{awwId}", method=RequestMethod.GET)
    public @ResponseBody SendWorkerProfileDTO fetchWorkerProfileDetails(@PathVariable String awwId){
        Long givenAwwId = Long.parseLong(awwId);

        Optional<AnganwaadiWorker> worker = anganwaadiWorkerService.getWorkerById(givenAwwId);
        SendWorkerProfileDTO nwWorker = new SendWorkerProfileDTO();

        if(worker.isEmpty()){
            nwWorker.setNullObj(true);
        }

        else {
            AnganwaadiWorker awWorker = worker.get();
            nwWorker.setNullObj(false);
            nwWorker.setName(awWorker.getName());
            nwWorker.setContactNumber(awWorker.getContactNumber());
            nwWorker.setAnganwaadiAddress(awWorker.getAnganwaadi().getAddress());
            nwWorker.setAnganwaadiLocation(awWorker.getAnganwaadi().getLocation());
            nwWorker.setUsername(awWorker.getRegDetails().getUserId());
            nwWorker.setEmail(awWorker.getRegDetails().getEmail());
            nwWorker.setRole(awWorker.getRegDetails().getRole());
        }


        return nwWorker;
    }


    @RequestMapping(value="/profileFollowup/{awwId}", method=RequestMethod.GET)
    public @ResponseBody SendFollowupLstDTO fetchWorkerFollowupLst(@PathVariable String awwId){
        Long givenAwwId = Long.parseLong(awwId);

        Optional<AnganwaadiWorker> worker = anganwaadiWorkerService.getWorkerById(givenAwwId);
        SendFollowupLstDTO lstFolObj = new SendFollowupLstDTO();

        if(worker.isEmpty()){
            lstFolObj.setNullObj(true);
        }

        else {
            AnganwaadiWorker awWorker = worker.get();
            lstFolObj.setNullObj(false);

            lstFolObj.setNwLstFollowups();
            List<Followup> angLstFollowups = awWorker.getLstFollowups();
            for(Followup fo: angLstFollowups){
                FollowupDTO folDto = new FollowupDTO();
                folDto.setFollowupId(fo.getFollowupId());
                folDto.setPatientId(fo.getDischargeSummary().getPatient().getSAMID());
                folDto.setDeadlineDate(fo.getDeadlineDate());
                folDto.setCompletedDate(fo.getCompletedDate());
                folDto.setHasCompleted(fo.getHasCompleted());
                lstFolObj.addToLstFollowups(folDto);
            }

        }

        return lstFolObj;

    }


    @RequestMapping(value="/patientProfile/{patientId}", method=RequestMethod.GET)
    public @ResponseBody SendPatientProfileDTO fetchPatientProfile(@PathVariable String patientId){
        Long givenId = Long.parseLong(patientId);

        SendPatientProfileDTO patientProfileObj = new SendPatientProfileDTO();

        patientProfileObj.setNullObj(true);

        // return patientProfileObj;
        Optional<Patient> patient = patientService.getPatientById(givenId);

        if(patient.isEmpty()){
            return patientProfileObj;
        }
        
        
        List<DischargeSummary> disSummary = disSumService.getSummaryByPatient(patient.get());
        
        if(disSummary.size()==0){
            return patientProfileObj;
        }
        
        DischargeSummary latestSummary = disSummary.get(disSummary.size() - 1);
        
        List<Followup> followups = followupService.getFollowupByDischargeSummary(latestSummary);
        
        if(followups.size()==0){
            return patientProfileObj;
        }
        
        patientProfileObj.setNullObj(false);
        patientProfileObj.setNwLstFollowups();
        
        for(Followup fo: followups){
            FollowupDTO folDto = new FollowupDTO();
            folDto.setFollowupId(fo.getFollowupId());
            folDto.setPatientId(fo.getDischargeSummary().getPatient().getSAMID());
            folDto.setDeadlineDate(fo.getDeadlineDate());
            folDto.setCompletedDate(fo.getCompletedDate());
            folDto.setHasCompleted(fo.getHasCompleted());
            folDto.setGender(patient.get().getGender());
            patientProfileObj.addToLstFollowups(folDto);
        }
        
        patientProfileObj.setLatestDischargeSummary(latestSummary);
        patientProfileObj.setCountDischarges(disSummary.size());
        
        return patientProfileObj;
    }


    @RequestMapping(value="/followupFormSubmission/{followupId}", method=RequestMethod.POST)
    public @ResponseBody SendValiditySubmissionDTO submitFollowupForm(@RequestBody recFormSubmissionDTO recForm, @PathVariable String followupId){
        SendValiditySubmissionDTO validForm = new SendValiditySubmissionDTO();
        validForm.setResult(false);

        Long givenFollowupId = Long.parseLong(followupId);

        Optional<Followup> fol = followupService.getFollowupById(givenFollowupId);
        if(fol.isEmpty()){
            return validForm;
        }

        HealthStatus nwHs = new HealthStatus();
        nwHs.setHeight(Double.parseDouble(recForm.getHeight()));
        nwHs.setWeight(Double.parseDouble(recForm.getWeight()));
        nwHs.setMUAC(Double.parseDouble(recForm.getMuac()));
        nwHs.setGrowthStatus(recForm.getGrowthStatus());
        nwHs.setOtherSymptoms(recForm.getOtherSymptoms());

        healthStatService.saveHealthStatus(nwHs);

        // fol.get().setHealthStatus(nwHs);

        // followupService.deleteFollowup(givenFollowupId);
        // fol.get().setFollowupId(givenFollowupId);
        // fol.get().setHasCompleted(true);
        LocalDate curDate = LocalDate.now();
        Date nwDate = Date.valueOf(curDate);
        int ret = followupService.updateFollowup(givenFollowupId, true, nwDate, nwHs.getHSID());

        System.out.println(ret);

        validForm.setResult(true);

        return validForm;
    }
    
}




// helper DTO
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class DischargeSummaryDTO{
    private Long dischargeId;
    private Date admissionDate;
    private Date dischargeDate;
    private Double admissionWt;
    private Double targetWt;
    private String outcome;
}


@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class FollowupDTO{
    private Long followupId;
    private Date deadlineDate;
    private Date completedDate;
    private Boolean hasCompleted;
    private Long patientId;
    private String gender;
}

// receive DTO
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class recFormSubmissionDTO{
    private String height;
    private String weight;
    private String muac;
    private String growthStatus;
    private String otherSymptoms;
}


// Send DTO
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendWorkerProfileDTO{
    private boolean nullObj;
    private String name;
    private String contactNumber;
    
    private String username;
    private String email;
    private String role;

    private String anganwaadiAddress;
    private String anganwaadiLocation;
}


@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendFollowupLstDTO{
    private boolean nullObj;
    private List<FollowupDTO> lstFollowups;

    void setNwLstFollowups(){
        lstFollowups = new ArrayList<FollowupDTO>();
    }

    void addToLstFollowups(FollowupDTO fol){
        lstFollowups.add(fol);
    }
}


@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendPatientProfileDTO{
    private boolean nullObj;
    private DischargeSummaryDTO latestDischargeSummary;
    private Integer countDischarges;
    private List<FollowupDTO> lstFollowups;
    
    void setNwLstFollowups(){
        lstFollowups = new ArrayList<FollowupDTO>();
    }

    void addToLstFollowups(FollowupDTO fol){
        lstFollowups.add(fol);
    }

    void setLatestDischargeSummary(DischargeSummary disSum){
        this.latestDischargeSummary = new DischargeSummaryDTO();
        this.latestDischargeSummary.setDischargeId(disSum.getDischargeId());
        this.latestDischargeSummary.setAdmissionDate(disSum.getAdmissionDate());
        this.latestDischargeSummary.setDischargeDate(disSum.getDischargeDate());
        this.latestDischargeSummary.setAdmissionWt(disSum.getAdmissionWt());
        this.latestDischargeSummary.setTargetWt(disSum.getTargetWt());
        this.latestDischargeSummary.setOutcome(disSum.getOutcome());
    }
}


@Data
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
class SendValiditySubmissionDTO{
    private boolean result;
}


