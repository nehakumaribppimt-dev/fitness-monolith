package com.example.fitness_monolith.dto;


import com.example.fitness_monolith.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    /* we are using userId bcz an activity belongs to a user through a many-to-one relnship.
    while creating an activity, the service needs some way to identify the owning user,so
    currently the client sends the userid. however, it is not mandatory to keep it in the request dto;
    the id could alternatively come from the pat variable or from the authenticated user's token
     */
    private String userId;
    private ActivityType type;
    private Map<String,Object> additionalMetrics;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
