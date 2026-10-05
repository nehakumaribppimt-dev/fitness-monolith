package com.example.fitness_monolith.service;

import com.example.fitness_monolith.dto.RecommendationRequest;
import com.example.fitness_monolith.dto.RecommendationResponse;
import com.example.fitness_monolith.model.Activity;
import com.example.fitness_monolith.model.Recommendation;
import com.example.fitness_monolith.model.User;
import com.example.fitness_monolith.repository.ActivityRepository;
import com.example.fitness_monolith.repository.RecommendationRepository;
import com.example.fitness_monolith.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public RecommendationResponse generateRecommendation(RecommendationRequest recommendationRequest) {

        User user= userRepository.findById(recommendationRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: " +recommendationRequest.getUserId()));

        Activity activity = activityRepository.findById(recommendationRequest.getActivityId())
                .orElseThrow(() -> new RuntimeException("Invalid user: " +recommendationRequest.getActivityId()));

        Recommendation rec= Recommendation.builder()
                .type(recommendationRequest.getType())
                .recommendation(recommendationRequest.getRecommendation())
                .improvements(recommendationRequest.getImprovements())
                .suggestions(recommendationRequest.getSuggestions())
                .safety(recommendationRequest.getSafety())
                .user(user)
                .activity(activity)
                .build();

        Recommendation saved = recommendationRepository.save(rec);

        return mapToResponse(saved);
    }

    private RecommendationResponse mapToResponse (Recommendation rec)
    {
        RecommendationResponse obj= new RecommendationResponse();

        obj.setType(rec.getType());
        obj.setRecommendation((rec.getRecommendation()));
        obj.setImprovements(rec.getImprovements());
        obj.setSuggestions(rec.getSuggestions());
        obj.setSafety(rec.getSafety());
        obj.setId(rec.getId());
        obj.setCreatedAt(rec.getCreatedAt());
        obj.setUpdatedAt(rec.getUpdatedAt());
        obj.setUserId(rec.getUser().getId());
        obj.setActivityId(rec.getActivity().getId());

        return obj;
    }

    public List<RecommendationResponse> getByUser(String userId) {

        List<Recommendation> recommendations = recommendationRepository.findByUserId(userId);

        return recommendations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<RecommendationResponse> getByActivities(String activityId) {

        List<Recommendation> recommendations = recommendationRepository.findByActivityId(activityId);

        return recommendations.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
