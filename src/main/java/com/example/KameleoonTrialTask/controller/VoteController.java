package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.service.VoteService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/api/votes")
@Data
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public void voteUp (@PathVariable Long quouteId, @RequestBody Long userId) throws NotFoundEx {
        voteService.voteUp(quouteId, userId);
    }

    @PostMapping
    public void voteDown (@PathVariable Long quouteId, @RequestBody Long userId) throws NotFoundEx {
        voteService.voteDown(quouteId, userId);
    }

    @GetMapping
    public GraphVoteDto getVoteHistory (@PathVariable Long quoteId) {
        return voteService.getVoteHistory(quoteId);
    }

}
