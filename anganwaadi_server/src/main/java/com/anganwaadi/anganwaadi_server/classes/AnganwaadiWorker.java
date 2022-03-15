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
    private Long aww_id; // anganwaadi worker id
    private String name;
    private String contact_number;
    
    @ManyToOne(targetEntity = Anganwaadi.class)
    @Setter @Getter
    private Anganwaadi anganwaadi; // anganwaadi
    
    @OneToOne(targetEntity = RegistrationDetails.class)
    @Setter @Getter
    private RegistrationDetails regDetails; // registration details


    public AnganwaadiWorker(String name, String contact_number){
        this.name = name;
        this.contact_number = contact_number;
    }

    // public void setAnganwaadi(Anganwaadi anganwaadi){
    //     this.anganwaadi = anganwaadi;
    // }

    // public void setRegDetails(RegistrationDetails regDetails){
    //     this.regDetails = regDetails;
    // }
}
