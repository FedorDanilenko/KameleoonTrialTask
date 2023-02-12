package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.QuoteAlreadyExistEx;
import com.example.KameleoonTrialTask.exception.UserNotFoundEx;
import com.example.KameleoonTrialTask.mapper.QuoteMapper;
import com.example.KameleoonTrialTask.repository.QuoteRepo;
import com.example.KameleoonTrialTask.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
