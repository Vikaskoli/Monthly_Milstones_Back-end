package com.vikas.MonthlyMilestones.controller;

import com.vikas.MonthlyMilestones.entity.Challenge;
import com.vikas.MonthlyMilestones.srvices.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:3000/")
public class ChallengeController {

    // Service layer dependency for business logic and data operations
    private final ChallengeService challengeService;
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>>getAllChallenges(){
        // Fetch all challenges through service layer
        List<Challenge> challenges = challengeService.getAllChallenges();
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        // Attempt to add challenge through service layer
        boolean isChallengeAdded = challengeService.addChallenge(challenge);

        // Return appropriate response based on addition result
        if (isChallengeAdded)
            return new ResponseEntity<>("challenge added successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("challenge NOT added !!!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        // Fetch challenge for specific month through service layer
        Challenge challenge = challengeService.getChallenge(month);

        // Return challenge if exists, otherwise return not found status
        if (challenge != null){
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable long id, @RequestBody Challenge updatedChallenge){
        // Attempt to update challenge through service layer
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedChallenge);

        // Return appropriate response based on update result
        if (isChallengeUpdated)
            return new ResponseEntity<>("challenge UPDATED successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("challenge NOT UPDATED !!!", HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable long id){
        // Attempt to delete challenge through service layer
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);

        // Return appropriate response based on deletion result
        if (isChallengeDeleted)
            return new ResponseEntity<>("challenge DELETED successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("challenge NOT DELETED !!!", HttpStatus.NOT_FOUND);
    }
}