package com.anganwaadi.anganwaadi_server.initialconfig;

import java.util.List;

import javax.annotation.PostConstruct;

import com.anganwaadi.anganwaadi_server.classes.Anganwaadi;
import com.anganwaadi.anganwaadi_server.classes.AnganwaadiWorker;
import com.anganwaadi.anganwaadi_server.classes.RegistrationDetails;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiRepository;
import com.anganwaadi.anganwaadi_server.repositories.AnganwaadiWorkerRepository;
import com.anganwaadi.anganwaadi_server.repositories.RegistrationDetailsRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(AnganwaadiRepository anganwaadiRepository, 
    AnganwaadiWorkerRepository anganwaadiWorkerRepository, RegistrationDetailsRepository registrationDetRepository){
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
    
            anganwaadiRepository.save(anganwaadi1);
    
            registrationDetRepository.saveAll(List.of(regWorker1, regWorker2, regWorker3)); 
            anganwaadiWorkerRepository.saveAll(List.of(worker1, worker2, worker3));

        };
    }

    public static void main(String[] args) {
        
    }
    
}
