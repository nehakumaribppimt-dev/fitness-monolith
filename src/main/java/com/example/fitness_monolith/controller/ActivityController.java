package com.example.fitness_monolith.controller;

import com.example.fitness_monolith.dto.ActivityRequest;
import com.example.fitness_monolith.dto.ActivityResponse;
import com.example.fitness_monolith.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping()
    public ResponseEntity<ActivityResponse> trackActivities(@RequestBody ActivityRequest activityRequest)
    {
        return ResponseEntity.ok(activityService.trackActivities(activityRequest));
    }

    @GetMapping
    // we can pass userid by param on postman, but we are passing through header,why?
    //BCZ generally query parameter is used for pagination etc.
    public ResponseEntity<List<ActivityResponse>> getUserActivities( @RequestHeader(value = "X-User-ID") String userId)
    {
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
