package com.example.fitness_monolith.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {

    private String type;
    private String recommendation;

    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
    private String userId;
    private String activityId;

}
