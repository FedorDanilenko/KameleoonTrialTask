package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuoteMapper {

    public QuoteEntity toQuote(QuoteInDto quoteInDto){
        return QuoteEntity.builder()
                .text(quoteInDto.getText())
                .build();
    }

    public QuoteOutDto toDto(QuoteEntity quoteEntity){
        return QuoteOutDto.builder()
                .id(quoteEntity.getId())
                .text(quoteEntity.getText())
                .score(quoteEntity.getScore())
                .build();
    }

    public QuoteDetailDto toDetail(QuoteEntity quote){
        return QuoteDetailDto.builder()
                .id(quote.getId())
                .text(quote.getText())
                .build();
    }

    public ListQuoteDto toListTop(List<QuoteEntity> quotes){
        return ListQuoteDto.builder()
                .quotes(quotes).build();
    }
}
