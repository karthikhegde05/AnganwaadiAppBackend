package com.anganwaadi.anganwaadi_server.classes;

import java.sql.Date;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Followup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FollowupId;
    private Date deadlineDate;
    private Date completedDate;
    private Boolean hasCompleted;

    // link one to one health status objects
    @OneToOne(targetEntity = HealthStatus.class)
    private HealthStatus healthStatus;

    @ManyToOne(targetEntity = DischargeSummary.class)
    private DischargeSummary dischargeSummary;

    public Followup(Date deadline){
        this.deadlineDate = deadline;
        this.hasCompleted = false;
    }

}
