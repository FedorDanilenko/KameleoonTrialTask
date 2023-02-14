package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.ListQuoteDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface QuoteMapper {

    @Mapping(target = "id", ignore = true)
    QuoteEntity toQuote(QuoteInDto quoteInDto);

    QuoteOutDto toDto(QuoteEntity quoteEntity);

    QuoteDetailDto toDetail(QuoteEntity quote);

    ListQuoteDto toList(List<QuoteEntity> quotes);
}
