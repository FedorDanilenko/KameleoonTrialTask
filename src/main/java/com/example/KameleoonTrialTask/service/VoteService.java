package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class VoteService {

    @Autowired
    private UserRepo userRepo;
    private QuoteRepo quoteRepo;
    private VoteRepo voteRepo;


    public void voteUp (Long quoteId, Long userId) {

    }
}
