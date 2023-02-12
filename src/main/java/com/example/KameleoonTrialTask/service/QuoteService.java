package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.QuoteAlreadyExistEx;
import com.example.KameleoonTrialTask.exception.QuoteNotFoundEx;
import com.example.KameleoonTrialTask.exception.UserNotFoundEx;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepo quoteRepo;
    private QuoteMapper quoteMapper;
    private UserRepo userRepo;

    public QuoteOutDto addQuote(QuoteInDto quoteInDto) throws QuoteAlreadyExistEx, UserNotFoundEx {
        UserEntity user = userRepo.findById(quoteInDto.getUserId())
                .orElseThrow(() -> new UserNotFoundEx("User Not Found"));
        QuoteEntity quote = quoteMapper.toQuote(quoteInDto);
        quote.setUser(user);
        try {
            return quoteMapper.toDto(quoteRepo.save(quote));
        } catch (Exception ex) {
            throw new QuoteAlreadyExistEx("Quote Already Exist");
        }
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

    private QuoteOutDto updateQuote(Long id, QuoteInDto quoteInDto) throws QuoteNotFoundEx {
        QuoteEntity quote = quoteRepo.findById(id)
                .orElseThrow(() -> new QuoteNotFoundEx("Quote not found"));
        quote.setText(quoteInDto.getText());
        quote.setDataCreated(LocalDateTime.now());
        return quoteMapper.toDto(quoteRepo.saveAndFlush(quote));
    }


}
