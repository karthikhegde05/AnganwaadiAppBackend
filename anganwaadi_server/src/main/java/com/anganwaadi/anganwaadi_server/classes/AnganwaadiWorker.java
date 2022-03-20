package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
public class AnganwaadiWorker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long awwId; // anganwaadi worker id
    private String name;
    private String contactNumber;
    
    @ManyToOne(targetEntity = Anganwaadi.class)
    @Setter @Getter
    private Anganwaadi anganwaadi; // anganwaadi
    
    @OneToOne(targetEntity = RegistrationDetails.class)
    @Setter @Getter
    private RegistrationDetails regDetails; // registration details


    public AnganwaadiWorker(String name, String contactNumber){
        this.name = name;
        this.contactNumber = contactNumber;
    }
}
