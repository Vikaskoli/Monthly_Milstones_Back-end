package com.vikas.MonthlyMilestones.repo;

//import org.springframework.data.repository.CrudRepository;

import com.vikas.MonthlyMilestones.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//CRUD
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByMonthIgnoreCase(String month);
}
