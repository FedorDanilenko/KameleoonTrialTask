package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.quote.QuoteDetailDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteInDto;
import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuoteMapper {

    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    @Mapping(target = "id", ignore = true)
    QuoteEntity toQuote(QuoteInDto quoteInDto);

    QuoteOutDto toDto(QuoteEntity quoteEntity);

    QuoteDetailDto toDetail(QuoteEntity quote);
}
