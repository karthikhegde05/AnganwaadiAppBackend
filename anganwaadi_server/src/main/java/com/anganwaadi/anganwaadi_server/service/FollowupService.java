package com.anganwaadi.anganwaadi_server.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Followup;
import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.repositories.FollowupRepository;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowupService {
    
    @NonNull
    private final FollowupRepository followupRepository;

    public List<Followup> getAllFollowups(){
        return followupRepository.findAll();
    }

    public Optional<Followup> getFollowupById(Long id){
        return followupRepository.findById(id);
    }

    public List<Followup> getFollowupByDischargeSummary(DischargeSummary disSummary){
        return followupRepository.findByDischargeSummaryOrderByDeadlineDateAsc(disSummary);
    }

    public void saveFollowup(Followup followup){
        followupRepository.saveAndFlush(followup);
    }

    public void deleteFollowup(Long id){
        followupRepository.deleteById(id);
    }

    @Transactional
    public int updateFollowup(Long id, boolean hasCompleted, Date completedDate, Long hsId){
        return followupRepository.updateFollowupByDateAndHS(id, hasCompleted, completedDate, hsId);
    }
}
