package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.service.VoteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/votes")
@Data
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/{id}/vote/up", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void voteUp (@PathVariable Long quouteId, @RequestBody Long userId) throws NotFoundEx {
        voteService.voteUp(quouteId, userId);
    }

    @PostMapping(value = "/{id}/vote/down", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void voteDown (@PathVariable Long quouteId, @RequestBody Long userId) throws NotFoundEx {
        voteService.voteDown(quouteId, userId);
    }

    @GetMapping(value = "/{id}/vote/history", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GraphVoteDto getVoteHistory (@PathVariable Long quoteId) {
        return voteService.getVoteHistory(quoteId);
    }

}
