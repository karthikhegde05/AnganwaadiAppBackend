package com.anganwaadi.anganwaadi_server.repositories;

import java.sql.Date;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Followup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowupRepository extends JpaRepository<Followup, Long>{
    List<Followup> findByDischargeSummaryOrderByDeadlineDateAsc(DischargeSummary disSummary);


    @Modifying
    @Query(value="UPDATE followup f SET f.has_completed = :hasCompleted, f.completed_date = :completedDate, f.health_status_hsid = :hsId WHERE f.followup_id = :FollowupId",
    nativeQuery=true)
    int updateFollowupByDateAndHS(@Param("FollowupId") Long FollowupId, @Param("hasCompleted") boolean hasCompleted, @Param("completedDate") Date completedDate, 
    @Param("hsId") Long hsId);
}
