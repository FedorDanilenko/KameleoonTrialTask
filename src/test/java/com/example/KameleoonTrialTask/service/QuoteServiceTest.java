package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.SortType;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.exception.NotFoundEx;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class QuoteServiceTest {


    @Autowired
    private QuoteRepo quoteRepo;
    @Autowired
    private QuoteMapper quoteMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VoteRepo voteRepo;
    @Autowired
    private QuoteService quoteService;

    @BeforeEach
    void clearDB() {
        userRepo.deleteAll();
        quoteRepo.deleteAll();
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
                .votes(null)
                .build();
        QuoteEntity quote2 = QuoteEntity.builder()
                .text("text2")
                .score(15L)
                .votes(null)
                .build();
        QuoteEntity quote3 = QuoteEntity.builder()
                .text("text3")
                .score(1L)
                .votes(null)
                .build();
        List<QuoteEntity> quotes = Arrays.asList(quote1,quote2,quote3);
        quoteRepo.saveAll(quotes);
    }
    @Test
    void addQuote() throws NotFoundEx, AlreadyExistEx {
        createUser();
        QuoteInDto quoteInDto = QuoteInDto.builder()
                .text("text")
                .userId(1L)
                .build();

        QuoteOutDto testResults = quoteService.addQuote(quoteInDto);

        assertNotNull(testResults.getId());
        assertEquals("text", testResults.getText());
        assertNull(testResults.getScore());
    }

    @Test
    void getQuote() throws NotFoundEx {
        createSomeQuotes();

        QuoteOutDto testResults = quoteService.getQuote(1L);

        assertEquals(1L, testResults.getId());
        assertEquals("text1", testResults.getText());

    }

    @Test
    void getTop() {
        createSomeQuotes();

        ListQuoteDto testResult1 = quoteService.getTop(SortType.TOP);

        assertEquals(3, testResult1.getQuotes().size());
        assertEquals(2L, testResult1.getQuotes().get(0).getId());

        ListQuoteDto testResult2 = quoteService.getTop(SortType.FLOP);

        assertEquals(3, testResult2.getQuotes().size());
        assertEquals(2L, testResult2.getQuotes().get(2).getId());

    }

    @Test
    void getRandomQuote() {
        createSomeQuotes();

        QuoteOutDto testResult = quoteService.getRandomQuote();

        assertNotNull(testResult.getId());
    }

    @Test
    void getQuoteDetail() throws NotFoundEx {
        createSomeQuotes();

        QuoteDetailDto testResult = quoteService.getQuoteDetail(1L);

        assertEquals("text1", testResult.getText());
    }

    @Test
    void updateQuote() throws NotFoundEx {
        createUser();
        createSomeQuotes();
        UserEntity user = userRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("User not found"));
        QuoteEntity quote = quoteRepo.findById(1L)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        quote.setUser(user);
        QuoteInDto quoteInDto = QuoteInDto.builder()
                .text("text-text")
                .userId(1L)
                .build();

        QuoteOutDto testResult = quoteService.updateQuote(1L,quoteInDto);

        assertEquals("text-text", testResult.getText());
        assertEquals(1L, testResult.getId());
    }

    @Test
    void deleteQuote() throws NotFoundEx {
        createSomeQuotes();

        quoteService.deleteQuote(2L);
        QuoteOutDto quote = quoteService.getQuote(1L);

        assertEquals(1L, quote.getId());
        assertThrows(NotFoundEx.class, () ->
                quoteService.getQuote(2l));
    }
}