package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.SortType;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.*;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.repository.VoteRepo;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@Validated
@Builder
public class QuoteService {

    private QuoteRepo quoteRepo;
    private QuoteMapper quoteMapper;
    private UserRepo userRepo;
    private VoteRepo voteRepo;

    @Autowired
    public QuoteService(QuoteRepo quoteRepo, QuoteMapper quoteMapper, UserRepo userRepo, VoteRepo voteRepo) {
        this.quoteRepo = quoteRepo;
        this.quoteMapper = quoteMapper;
        this.userRepo = userRepo;
        this.voteRepo = voteRepo;
    }

    public QuoteOutDto addQuote(QuoteInDto quoteInDto) throws AlreadyExistEx, NotFoundEx {
        UserEntity user = userRepo.findById(quoteInDto.getUserId())
                .orElseThrow(() -> new NotFoundEx("User Not Found"));
        QuoteEntity quote = quoteMapper.toQuote(quoteInDto);
        quote.setUser(user);
        try {
            return quoteMapper.toDto(quoteRepo.save(quote));
        } catch (Exception ex) {
            throw new AlreadyExistEx("Quote Already Exist");
        }
    }

    public QuoteOutDto getQuote (Long id) throws NotFoundEx {
        return quoteMapper.toDto(quoteRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Quote not found")));
    }

    public ListQuoteDto getTop(SortType sortType) {
        List<QuoteEntity> quotes = quoteRepo.findAll();
        if (sortType == SortType.TOP) {
            quotes.sort((q1, q2) -> (int) (q2.getScore() - q1.getScore()));
        } else {
            quotes.sort((q1, q2) -> (int) (q1.getScore() - q2.getScore()));
        }
        if (quotes.size() > 10) {
            quotes = quotes.subList(0, 10);
        }
        return quoteMapper.toListTop(quotes);
    }

    public QuoteOutDto getRandomQuote() {
        List<QuoteEntity> quotes = quoteRepo.findAll();
        int size = quotes.size();
        if (size == 0) {
            return null;
        }
        int randomIndex = new Random().nextInt(size);
        QuoteEntity quote = quotes.get(randomIndex);
        return quoteMapper.toDto(quote);
    }

    public QuoteDetailDto getQuoteDetail(Long id) throws NotFoundEx {
        return quoteMapper.toDetail(quoteRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Quote not found")));
    }
    @Transactional
    public QuoteOutDto updateQuote(Long id, QuoteInDto quoteInDto) throws NotFoundEx {
        QuoteEntity quote = quoteRepo.findByIdAndUserId(id, quoteInDto.getUserId())
                .orElseThrow(() -> new NotFoundEx("Quote Not Found"));
        quote.setText(quoteInDto.getText());
        quote.setDataCreated(LocalDateTime.now());
        return quoteMapper.toDto(quoteRepo.saveAndFlush(quote));
    }

    @Transactional
    public void deleteQuote (Long id) throws NotFoundEx {
        QuoteEntity quote = quoteRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        voteRepo.deleteByQuote(quote);
        quoteRepo.delete(quote);
    }



}
