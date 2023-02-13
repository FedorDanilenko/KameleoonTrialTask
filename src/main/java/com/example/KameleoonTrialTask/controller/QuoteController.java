package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.exception.*;
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
    public QuoteOutDto addQuote (@Valid @RequestBody QuoteInDto quoteInDto) throws NotFoundEx, AlreadyExistEx {
        return quoteService.addQuote(quoteInDto);
    }

    @GetMapping
    public QuoteOutDto getQuote(Long id) throws NotFoundEx {
        return quoteService.getQuote(id);
    }

    @GetMapping
    public QuoteDetailDto getDetail(Long id) throws NotFoundEx {
        return quoteService.getQuoteDetail(id);
    }

    @GetMapping
    public QuoteOutDto getRandomQuote() {
        return quoteService.getRandomQuote();
    }

    @PutMapping("/{id}")
    public QuoteOutDto update(@PathVariable("id") Long id, @Valid @RequestBody QuoteInDto quoteInDto) throws NotFoundEx {
        return quoteService.updateQuote(id, quoteInDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) throws NotFoundEx {
        quoteService.deleteQuote(id);
    }
}
