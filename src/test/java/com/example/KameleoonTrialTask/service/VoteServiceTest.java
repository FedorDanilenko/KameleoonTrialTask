package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.entity.VoteEntity;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.mapper.VoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class VoteServiceTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuoteRepo quoteRepo;
    @Autowired
    private VoteRepo voteRepo;
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private VoteService voteService;

    @BeforeEach
    void clearDB() {
        userRepo.deleteAll();
        quoteRepo.deleteAll();
        voteRepo.deleteAll();
    }


    private void createUser() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .name("login")
                .build();
        userRepo.save(user);
    }

    private void createSomeQuotes() {
        QuoteEntity quote1 = QuoteEntity.builder()
                .text("text1")
                .score(4L)
                .build();
        QuoteEntity quote2 = QuoteEntity.builder()
                .text("text2")
                .score(15L)
                .build();
        QuoteEntity quote3 = QuoteEntity.builder()
                .text("text3")
                .score(1L)
                .build();
        List<QuoteEntity> quotes = Arrays.asList(quote1,quote2,quote3);
        quoteRepo.saveAll(quotes);
    }

    @Test
    void voteUp() throws NotFoundEx {
        createUser();
        createSomeQuotes();
        UserEntity user = userRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = quoteRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        quote.setUser(user);
        quoteRepo.saveAndFlush(quote);

        voteService.voteUp(1L, 1L);
        QuoteEntity quoteRes = quoteRepo.findByIdAndUserId(1L,1L)
                        .orElseThrow(() -> new NotFoundEx("Quote not found"));
        VoteEntity vote = voteRepo.findById(1L)
                        .orElseThrow(() -> new NotFoundEx("Vote not exists"));

        assertEquals(1L, quoteRes.getId());
        assertEquals(5L, quoteRes.getScore());
        assertEquals(1L, vote.getUser().getId());
        assertEquals(1L, vote.getQuote().getUser().getId());
    }

    @Test
    void voteDown() throws NotFoundEx {
        createUser();
        createSomeQuotes();
        UserEntity user = userRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = quoteRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        quote.setUser(user);
        quoteRepo.saveAndFlush(quote);

        voteService.voteDown(1L, 1L);
        QuoteEntity quoteRes = quoteRepo.findByIdAndUserId(1L,1L)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        VoteEntity vote = voteRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("Vote not exists"));

        assertEquals(1L, quoteRes.getId());
        assertEquals(3L, quoteRes.getScore());
        assertEquals(1L, vote.getUser().getId());
        assertEquals(1L, vote.getQuote().getUser().getId());
    }

    @Test
    void getVoteHistory() throws NotFoundEx {
        createUser();
        UserEntity user = userRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = QuoteEntity.builder()
                .text("text")
                .score(0L)
                .user(user)
                .build();
        quoteRepo.save(quote);

        voteService.voteUp(1L,1L);
        voteService.voteDown(1L,1L);

        GraphVoteDto testResult = voteService.getVoteHistory(1L);

        assertEquals(HashMap.class, testResult.getMap().getClass());
    }
}