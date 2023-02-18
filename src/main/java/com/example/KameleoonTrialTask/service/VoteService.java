package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.entity.VoteEntity;
import com.example.KameleoonTrialTask.entity.VoteType;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.mapper.VoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuoteRepo quoteRepo;
    @Autowired
    private VoteRepo voteRepo;

    private VoteMapper voteMapper;


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

    public GraphVoteDto getVoteHistory(Long qouteId) {
        List<VoteEntity> votes = voteRepo.findAllByQuoteId(qouteId);
        Map<Timestamp, Integer> voteHistory = new HashMap<>();
        for (VoteEntity vote : votes) {
            Timestamp timestamp = Timestamp.valueOf(vote.getDataCreated());
            if (voteHistory.containsKey(timestamp)) {
                if (vote.getType() == VoteType.UP) {
                    voteHistory.put(timestamp, voteHistory.get(timestamp) + 1);
                } else {
                    voteHistory.put(timestamp, voteHistory.get(timestamp) - 1);
                }
            } else {
                if (vote.getType() == VoteType.UP) {
                    voteHistory.put(timestamp, 1);
                } else {
                    voteHistory.put(timestamp, -1);
                }
            }
        }
        return voteMapper.toGraphVoteDto(voteHistory);
    }
}
