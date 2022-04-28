package com.anganwaadi.anganwaadi_server.classes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data @NoArgsConstructor
@Table(name = "follow_up")
public class FollowUp {
    
    @SequenceGenerator(
		name = "followup_sequence",
		sequenceName = "followup_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "followup_sequence"
	)
    @Id
    private Long followupId;
    
    @ManyToOne
    @JoinColumn(name = "sam_id")
    private Patient patient;
    
    @ManyToOne(targetEntity = AnganwadiWorker.class)
    @JoinColumn(name = "aww_id")
    @Setter @Getter
    private AnganwadiWorker anganwaadiWorker;

    private LocalDateTime deadlineDate;
    private LocalDateTime completedDate;

    @OneToOne(targetEntity = HealthStatus.class)
    @JoinColumn(name = "hs_id")
    @Setter @Getter
    private HealthStatus healthStatus;

    private Boolean completed;

    private LocalDateTime createdDate;

    public FollowUp(Patient patient, LocalDateTime deadline_date, LocalDateTime completed_date,
    HealthStatus healthStatus, Boolean completed, LocalDateTime createdDate){
        this.patient=patient;
        this.deadlineDate=deadline_date;
        this.completedDate=completed_date;
        this.healthStatus=healthStatus;
        this.completed=completed;
        this.createdDate=createdDate;
    }
    
    

}
