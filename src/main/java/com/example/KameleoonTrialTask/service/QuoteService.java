package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.*;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepo quoteRepo;
    private QuoteMapper quoteMapper;
    private UserRepo userRepo;

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


    @Transactional
    public QuoteOutDto updateQuote(Long id, QuoteInDto quoteInDto) throws NotFoundEx {
        QuoteEntity quote = quoteRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        quote.setText(quoteInDto.getText());
        quote.setDataCreated(LocalDateTime.now());
        return quoteMapper.toDto(quoteRepo.saveAndFlush(quote));
    }

    @Transactional
    public void deleteQuote (Long id) throws NotFoundEx {
        QuoteEntity quote = quoteRepo.findById(id)
                .orElseThrow(() -> new NotFoundEx("Quote not found"));
        quoteRepo.delete(quote);
    }



}
