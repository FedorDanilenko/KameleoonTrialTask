package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.entity.VoteEntity;
import com.example.KameleoonTrialTask.entity.VoteType;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private UserRepo userRepo;
    private QuoteRepo quoteRepo;
    private VoteRepo voteRepo;


    public void voteUp (Long quoteId, Long userId) throws NotFoundEx {
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = quoteRepo.findById(quoteId)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        VoteEntity vote = voteRepo.findByQuoteIdAndUserId(quoteId, userId);
        if (vote != null && vote.getType() == VoteType.DOWN) {
            vote.setType(VoteType.UP);
            quote.setScore(quote.getScore() + 1);
            quoteRepo.saveAndFlush(quote);
        } else {
            vote = new VoteEntity();
            vote.setType(VoteType.UP);
            vote.setUser(user);
            vote.setQuote(quote);
            quote.setScore(quote.getScore() + 1);
            quoteRepo.saveAndFlush(quote);
        }
    }
    public void voteDown (Long quoteId, Long userId) throws NotFoundEx {
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = quoteRepo.findById(quoteId)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        VoteEntity vote = voteRepo.findByQuoteIdAndUserId(quoteId, userId);
        if (vote != null && vote.getType() == VoteType.UP) {
            vote.setType(VoteType.DOWN);
            quote.setScore(quote.getScore() - 1);
            quoteRepo.saveAndFlush(quote);
        } else {
            vote = new VoteEntity();
            vote.setType(VoteType.DOWN);
            vote.setUser(user);
            vote.setQuote(quote);
            quote.setScore(quote.getScore() - 1);
            quoteRepo.saveAndFlush(quote);
        }
    }

    public List<VoteEntity> getVoteHistory (Long quoteId) {
        return voteRepo.findAllByQuoteIdOrderByTimestampAsc(quoteId);
    }
}
