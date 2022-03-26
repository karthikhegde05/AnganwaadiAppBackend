package com.anganwaadi.anganwaadi_server.classes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @NoArgsConstructor
public class FollowUp {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long follow_up_id;
    
    private Long SAM_ID;
    
    @ManyToOne(targetEntity = AnganwaadiWorker.class)
    @Setter @Getter
    private AnganwaadiWorker anganwaadiWorker;

    private LocalDateTime deadline_date;
    private LocalDateTime completed_date;

    @OneToOne(targetEntity = HealthStatus.class)
    @Setter @Getter
    private HealthStatus healthStatus;

    private Boolean completed;

    private LocalDateTime createdDate;

    public FollowUp(Long SAM_ID, LocalDateTime deadline_date, LocalDateTime completed_date,
    HealthStatus healthStatus, Boolean completed, LocalDateTime createdDate){
        this.SAM_ID=SAM_ID;
        this.deadline_date=deadline_date;
        this.completed_date=completed_date;
        this.healthStatus=healthStatus;
        this.completed=completed;
        this.createdDate=createdDate;
    }
    
    

}
