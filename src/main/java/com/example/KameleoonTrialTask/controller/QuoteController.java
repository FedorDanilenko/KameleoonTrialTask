package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.exception.QuoteAlreadyExistEx;
import com.example.KameleoonTrialTask.exception.QuoteNotFoundEx;
import com.example.KameleoonTrialTask.exception.UserNotFoundEx;
import com.example.KameleoonTrialTask.service.QuoteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/quotes")
@Data
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public QuoteOutDto addQuote (@Valid @RequestBody QuoteInDto quoteInDto) throws UserNotFoundEx, QuoteAlreadyExistEx {
        return quoteService.addQuote(quoteInDto);
    }

    @GetMapping
    public QuoteOutDto getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @PostMapping("/{id}")
    public QuoteOutDto update(@PathVariable("id") Long id, @Valid @RequestBody QuoteInDto quoteInDto) throws QuoteNotFoundEx {
        return quoteService.updateQuote(id, quoteInDto);
    }
}
