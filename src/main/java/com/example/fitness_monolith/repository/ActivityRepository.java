package com.example.fitness_monolith.repository;

import com.example.fitness_monolith.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {

    /* hibernate itself understand that , findby-> is select query with where clause, field name = userId.
     therefore, it will retrive all activities of specific userid */
    List<Activity> findByUserId(String userId);
}
