package com.anganwaadi.anganwaadi_server.repositories;

// import java.time.LocalDateTime;
// import java.util.List;

// import com.anganwaadi.anganwaadi_server.classes.AnganwadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DischargeRepository extends JpaRepository<DischargeSummary, String>{
    // List<FollowUp> findByAnganwaadiWorkerAndCreatedDateGreaterThan(AnganwadiWorker a, LocalDateTime t);
    // List<FollowUp> findByPatientAndCreatedDateGreaterThanAndCompletedIsTrue(Patient p, LocalDateTime t);
    DischargeSummary findTop1ByPatientOrderByCreatedDateDesc(Patient p);
}
