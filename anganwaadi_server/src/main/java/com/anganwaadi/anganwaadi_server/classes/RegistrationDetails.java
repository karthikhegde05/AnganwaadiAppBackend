package com.anganwaadi.anganwaadi_server.classes;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class RegistrationDetails {
    
    @SequenceGenerator(
		name = "registration_details_sequence",
		sequenceName = "registration_details_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "registration_details_sequence"
	)
    @Id
    private String userId;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role; // Role based authentication control

    public RegistrationDetails(String userId, String email, String firstName, String lastName, String password, String role){
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

}
