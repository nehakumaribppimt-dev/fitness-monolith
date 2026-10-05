package com.example.fitness_monolith.controller;

import com.example.fitness_monolith.dto.RegisterRequest;
import com.example.fitness_monolith.dto.UserResponse;
import com.example.fitness_monolith.model.User;
import com.example.fitness_monolith.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*this class will handle HTTP REST API requests. When app starts, Spring creates its bean.
                   return value of methods converted into JSON automatically */
@RequestMapping("/api/auth")  //base url for every endpoint inside this controller
@RequiredArgsConstructor // it will create constructor for class which is declared final
public class AuthController {

    private final UserService userService; // declared final so that @RequiredConstructor can create constructor for it

    @PostMapping("/register") /* run this method when POST request comes to /api/auth/register
                                POST is normally used when we are submitting data/create a resource */
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) /* @RequestBody take the JSON from
                                                                           HTTP request body and convert it into respective java object */
    {
        /* return userService.register(registerRequest); /* Controller class says- i received the registration request.
                                                          UserService class, you handle the business logic for registration*/
        //return ResponseEntity.ok(userService.register(registerRequest));
        UserResponse response =userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
