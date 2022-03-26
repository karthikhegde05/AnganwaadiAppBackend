package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Anganwaadi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long awId;
    private String address;
    private String location; // City/location

    public Anganwaadi(String address, String location){
        this.address = address;
        this.location = location;
    }

}
