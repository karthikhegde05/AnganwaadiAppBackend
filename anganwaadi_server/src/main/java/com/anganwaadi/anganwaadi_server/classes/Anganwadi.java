package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Anganwadi {

    @SequenceGenerator(
		name = "anganwadi_sequence",
		sequenceName = "anganwadi_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "anganwadi_sequence"
	)
    @Id
    private Long awId;
    private String address;
    private String location; // City/location

    public Anganwadi(String address, String location){
        this.address = address;
        this.location = location;
    }

}
