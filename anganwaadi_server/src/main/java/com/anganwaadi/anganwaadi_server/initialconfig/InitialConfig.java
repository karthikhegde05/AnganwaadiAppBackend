package com.anganwaadi.anganwaadi_server.initialconfig;

import java.sql.Date;
import java.util.List;

import com.anganwaadi.anganwaadi_server.classes.Anganwaadi;
import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.classes.DischargeSummary;
import com.anganwaadi.anganwaadi_server.classes.Followup;
import com.anganwaadi.anganwaadi_server.classes.HealthStatus;
import com.anganwaadi.anganwaadi_server.classes.Patient;
import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiRepository;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiWorkerRepository;
import com.anganwaadi.anganwaadi_server.repositories.DischargeSummaryRepository;
import com.anganwaadi.anganwaadi_server.repositories.FollowupRepository;
import com.anganwaadi.anganwaadi_server.repositories.HealthStatusRepository;
import com.anganwaadi.anganwaadi_server.repositories.PatientRepository;
import com.anganwaadi.anganwaadi_server.repositories.RegistrationDetailsRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnganwaadiRepository anganwaadiRepository, 
    AnganwaadiWorkerRepository anganwaadiWorkerRepository, RegistrationDetailsRepository registrationDetRepository,
    PatientRepository patientRepository, FollowupRepository followupRepository, DischargeSummaryRepository disSumRepository,
    HealthStatusRepository hsRepository){
        return args -> {
            AnganwaadiWorker worker1 = new AnganwaadiWorker("Hemanth", "8965");
            AnganwaadiWorker worker2 = new AnganwaadiWorker("Jishnu", "8955");
            AnganwaadiWorker worker3 = new AnganwaadiWorker("Karthik", "8924");
    
            Anganwaadi anganwaadi1 = new Anganwaadi("Electronic-city, South Bangalore", "Bangalore");
    
            RegistrationDetails regWorker1 = new RegistrationDetails("hem123", "hem@gmail.com", "hemanth", "chitti","pass123", "worker");
            RegistrationDetails regWorker2 = new RegistrationDetails("jish123", "jish@gmail.com", "jishnu", "kumar", "pass234", "worker");
            RegistrationDetails regWorker3 = new RegistrationDetails("kar123", "kar@gmail.com", "karthik", "hegde","pass456", "worker");
    
            worker1.setRegDetails(regWorker1);
            worker2.setRegDetails(regWorker2);
            worker3.setRegDetails(regWorker3);
    
            worker1.setAnganwaadi(anganwaadi1); worker2.setAnganwaadi(anganwaadi1);
            worker3.setAnganwaadi(anganwaadi1);
    
            
            Patient pat1 = new Patient("Uhid1", "rchid1", "name1", "22", Date.valueOf("2006-06-10"), "M", "add1", "city1", "892", "Single", "caste1", "religion1", "bpl", "ang");
            Patient pat2 = new Patient("Uhid2", "rchid2", "name2", "22", Date.valueOf("2007-06-10"), "F", "add2", "city2", "882", "Single", "caste2", "religion2", "bpl", "ang");
            
            HealthStatus adHS1 = new HealthStatus((double)22, (double)20, (double)15, "no growth", "full symptoms");
            HealthStatus adHS2 = new HealthStatus((double)20, (double)25, (double)10, "no growth", "full symptoms");
            
            pat1.setAdmissionHS(List.of(adHS1));
            pat2.setAdmissionHS(List.of(adHS2));
            
            DischargeSummary disS1 = new DischargeSummary(Date.valueOf("2022-03-22"), (double)20, (double)50, Date.valueOf("2022-04-10"), "better");
            DischargeSummary disS2 = new DischargeSummary(Date.valueOf("2022-03-20"), (double)20, (double)50, Date.valueOf("2022-04-05"), "better");
            
            disS1.setPatient(pat1);
            disS2.setPatient(pat2);
            
            Followup fo11 = new Followup(Date.valueOf("2022-05-20"));
            Followup fo12 = new Followup(Date.valueOf("2022-06-20"));
            Followup fo21 = new Followup(Date.valueOf("2022-05-15"));
            Followup fo22 = new Followup(Date.valueOf("2022-06-15"));
            
            HealthStatus hs11 = new HealthStatus();
            HealthStatus hs12 = new HealthStatus();
            HealthStatus hs21 = new HealthStatus();
            HealthStatus hs22 = new HealthStatus();
            
            fo11.setHealthStatus(hs11);
            fo12.setHealthStatus(hs12);
            fo21.setHealthStatus(hs21);
            fo22.setHealthStatus(hs22);
            
            fo11.setDischargeSummary(disS1);
            fo12.setDischargeSummary(disS1);
            fo21.setDischargeSummary(disS2);
            fo22.setDischargeSummary(disS2);
            
            worker1.setLstFollowups(List.of(fo11, fo12));
            worker2.setLstFollowups(List.of(fo21, fo22));
            
            anganwaadiRepository.saveAndFlush(anganwaadi1);
    
            registrationDetRepository.saveAllAndFlush(List.of(regWorker1, regWorker2, regWorker3)); 
            hsRepository.saveAllAndFlush(List.of(adHS1, adHS2, hs11, hs12, hs21, hs22));
            patientRepository.saveAllAndFlush(List.of(pat1, pat2));
            disSumRepository.saveAllAndFlush(List.of(disS1, disS2));
            followupRepository.saveAllAndFlush(List.of(fo11, fo12, fo21, fo22));
            anganwaadiWorkerRepository.saveAllAndFlush(List.of(worker1, worker2, worker3));
            
            
        };
    }

    public static void main(String[] args) {
        
    }
    
}
