package com.example.KameleoonTrialTask.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@Data
public class VoteController {

    @Autowired
    private VoteServise voteServise;
}
