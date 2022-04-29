package com.anganwaadi.anganwaadi_server.controller.DTO;

import java.time.LocalDateTime;

import com.anganwaadi.anganwaadi_server.classes.FollowUp;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public
class FollowUpDTO{

    private Long followupId;
    private Long samId;

    private LocalDateTime deadline_date;
    private LocalDateTime completed_date;

    @Setter @Getter
    private HealthStatusDTO healthStatus;
    // private Long hsId;



    private Boolean completed;

    private LocalDateTime createdDate;

    private Long workerId;

    public FollowUpDTO(FollowUp followUp){

        this.followupId = followUp.getFollowupId();
        this.samId = followUp.getPatient().getSamId();
        this.deadline_date = followUp.getDeadlineDate();
        this.completed_date = followUp.getCompletedDate();

        this.healthStatus = new HealthStatusDTO(followUp.getHealthStatus());
        // this.hsId = followUp.getHealthStatus().getHsId();
    

        this.completed = followUp.getCompleted();
        this.createdDate =followUp.getCreatedDate();

        this.workerId = followUp.getAnganwaadiWorker().getAwwId();
    }
}
