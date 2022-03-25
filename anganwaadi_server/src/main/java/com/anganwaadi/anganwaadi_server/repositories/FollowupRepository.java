package com.anganwaadi.anganwaadi_server.repositories;

import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Followup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowupRepository extends JpaRepository<Followup, Long>{
    List<Followup> findByDischargeSummaryOrderByDeadlineDateAsc(DischargeSummary disSummary);
}
