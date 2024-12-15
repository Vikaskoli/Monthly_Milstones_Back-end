package com.vikas.MonthlyMilestones.srvices;

import com.vikas.MonthlyMilestones.entity.Challenge;
import com.vikas.MonthlyMilestones.repo.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    private long nextId = 1L;

    @Autowired
    private ChallengeRepository challengeRepository;

    // Get all challenges (non-static)
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    // Add a new challenge
    public boolean addChallenge(Challenge challenge) {
        challenge.setId(nextId++);
        if (challenge != null) {
            challengeRepository.save(challenge); // Save challenge
            return true;
        } else {
            return false;
        }
    }

    // Get a specific challenge by month (non-static)
    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    // Update an existing challenge
    public boolean updateChallenge(long id, Challenge updatedChallenge) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());
            challengeRepository.save(challengeToUpdate); // Save updated challenge
            return true;
        }
        return false;
    }

    // Delete a challenge
    public boolean deleteChallenge(long id) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            challengeRepository.deleteById(id); // Delete challenge
            return true;
        }
        return false;
    }
}
