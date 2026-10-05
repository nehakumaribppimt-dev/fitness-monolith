package com.example.fitness_monolith.service;

import com.example.fitness_monolith.dto.ActivityRequest;
import com.example.fitness_monolith.dto.ActivityResponse;
import com.example.fitness_monolith.model.Activity;
import com.example.fitness_monolith.model.User;
import com.example.fitness_monolith.repository.ActivityRepository;
import com.example.fitness_monolith.repository.UserRepository;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    public ActivityResponse trackActivities(ActivityRequest activityRequest)
    {

        /* Activity has a ManyToOne relationship with User, so the Activity entity stores a User reference rather than just a user ID.
         The client provides the user ID in ActivityRequest. In this service layer, I use userRepository.findById() to retrieve the
         corresponding User entity using userid from db . Since findById() returns an Optional, I use orElseThrow() to reject an invalid user ID.
          I then assign the retrieved User to the Activity using the builder
         */
        User user = userRepository.findById(activityRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: " +activityRequest.getUserId()));

        //obj of activity is getting created with the input(request) which we get from client
        Activity activity= Activity.builder()
                .user(user)
                .type(activityRequest.getType())
                .startTime(activityRequest.getStartTime())
                .endTime(activityRequest.getEndTime())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .duration(activityRequest.getDuration())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();

        //then we store it in db
        Activity savedActivity =activityRepository.save(activity);

        // we will create this method for mapping the stored data in db to response and will return the response
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity savedActivity)
    {
        ActivityResponse activityResponse= new ActivityResponse();

        activityResponse.setType(savedActivity.getType());
        activityResponse.setCreatedAt(savedActivity.getCreatedAt());
        activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setCaloriesBurned(savedActivity.getCaloriesBurned());
        activityResponse.setStartTime(savedActivity.getStartTime());
        activityResponse.setEndTime(savedActivity.getEndTime());
        activityResponse.setId(savedActivity.getId());
        activityResponse.setUserId(savedActivity.getUser().getId());
        activityResponse.setAdditionalMetrics(savedActivity.getAdditionalMetrics());

        return activityResponse;
    }

    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);

        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}