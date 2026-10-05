package com.example.fitness_monolith.repository;

import com.example.fitness_monolith.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/* <User, String>  -> the repo manages the user entity , and datatype of its primary key is String (Id)
JpaRepository provide few readymade methods to perform db operations eg-save(),count(),findAll() etc */

/* we are creating interface , not concreate class for repo bcz - we dont need to provide implementation of abstract methods
and Spring data JPA creates a concreate implementation/proxy at runtime

 */
public interface UserRepository extends JpaRepository<User, String> {
}
