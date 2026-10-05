package com.example.fitness_monolith.controller;

import com.example.fitness_monolith.dto.RecommendationRequest;
import com.example.fitness_monolith.dto.RecommendationResponse;
import com.example.fitness_monolith.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@RequestBody RecommendationRequest recommendationRequest)
    {
        return ResponseEntity.ok(recommendationService.generateRecommendation(recommendationRequest));
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecommendationResponse>> getByUser(@RequestHeader (value = "X-User-ID") String userId)
    {
        return ResponseEntity.ok(recommendationService.getByUser(userId));
    }

    @GetMapping("/activities")
    public ResponseEntity<List<RecommendationResponse>> getByActivities(@RequestHeader(value ="Y-Activity-ID") String activityId)
    {
        return ResponseEntity.ok(recommendationService.getByActivities(activityId));
    }
}
