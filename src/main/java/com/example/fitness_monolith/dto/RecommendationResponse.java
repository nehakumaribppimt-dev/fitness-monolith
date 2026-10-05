package com.example.fitness_monolith.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponse {

    private String type;
    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
    private String userId;
    private String activityId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String id;
}
