package com.example.fitness_monolith.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*
this class defines what the client sends to our Api for registration of user
during registration, fields like- id, createdAt,updatedAt,activities,recommendations should not be controlled by users
it should be controlled by app/db.
this is a request DTO, used to transfer registration data from the client to backend. Instead of exposing the complete
user entity to the api, it containes only fields required for registration.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
