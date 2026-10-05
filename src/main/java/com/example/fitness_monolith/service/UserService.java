package com.example.fitness_monolith.service;

import com.example.fitness_monolith.dto.RegisterRequest;
import com.example.fitness_monolith.dto.UserResponse;
import com.example.fitness_monolith.model.User;
import com.example.fitness_monolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {

        User user =User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .dob(registerRequest.getDob())
                .build();
        /*User user=new User(
                null, // id is null bcz db generates id
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                Instant.parse("2026-09-03T13:14:41.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                Instant.parse("2026-09-03T13:14:41.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                registerRequest.getDob(),
                List.of(),
                List.of()
        );*/

        User savedUser = userRepository.save(user);
       return mapToResponse(savedUser); // it will map user obj into response structure
    }

    /* mapToResponse method is private bcz it is helper method which is only used in this service class
    so private access modifier will prevent other class to use this method
    and we can user builder() for creating obj if you want
     */
    private UserResponse mapToResponse(User savedUser) {

        UserResponse response= new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        response.setAge(Period.between(savedUser.getDob(), LocalDate.now()).getYears());
        return response;
    }
}
