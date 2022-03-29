package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Setter @NoArgsConstructor
public class AnganwadiWorker {
    
    @SequenceGenerator(
		name = "anganwadi_worker_sequence",
		sequenceName = "anganwadi_worker_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "anganwadi_worker_sequence"
	)
    @Id
    private Long awwId; // anganwaadi worker id
    private String name;
    private String contactNumber;
    
    @ManyToOne(targetEntity = Anganwadi.class)
    @JoinColumn(name = "aw_id")
    @Setter @Getter
    private Anganwadi anganwadi; // anganwadi
    
    @OneToOne(targetEntity = RegistrationDetails.class)
    @JoinColumn(name = "user_id")
    @Setter @Getter
    private RegistrationDetails regDetails; // registration details


    public AnganwadiWorker(String name, String contactNumber){
        this.name = name;
        this.contactNumber = contactNumber;
    }
}
