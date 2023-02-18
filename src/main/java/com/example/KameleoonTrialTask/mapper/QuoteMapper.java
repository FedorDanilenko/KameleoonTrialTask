package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;

import java.util.List;

public class QuoteMapper {

    public static QuoteEntity toQuote(QuoteInDto quoteInDto){
        return QuoteEntity.builder()
                .id(quoteInDto.getUserId())
                .text(quoteInDto.getText())
                .build();
    }

    public static QuoteOutDto toDto(QuoteEntity quoteEntity){
        return QuoteOutDto.builder()
                .id(quoteEntity.getId())
                .text(quoteEntity.getText())
                .score(quoteEntity.getScore())
                .build();
    }

    public static QuoteDetailDto toDetail(QuoteEntity quote){
        return QuoteDetailDto.builder()
                .id(quote.getId())
                .text(quote.getText())
                .build();
    }

    public static ListQuoteDto toListTop(List<QuoteEntity> quotes){
        return ListQuoteDto.builder()
                .quotes(quotes).build();
    }
}
