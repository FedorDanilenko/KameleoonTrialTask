package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.exception.QuoteAlreadyExistEx;
import com.example.KameleoonTrialTask.exception.UserNotFoundEx;
import com.example.KameleoonTrialTask.service.QuoteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import javax.validation.Valid;

@RestController
@RequestMapping("/api/quotes")
@Data
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    public void addQuote (@Valid @RequestBody QuoteInDto quoteInDto) throws UserNotFoundEx, QuoteAlreadyExistEx {
        quoteService.addQuote(quoteInDto);
    }
}
