package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.vote.VoteInDto;
import com.example.KameleoonTrialTask.dto.vote.VoteOutDto;
import com.example.KameleoonTrialTask.entity.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    @Mapping(target = "dataCreated", expression = "java(java.time.LocalDateTime.now())")
    VoteEntity toVote(VoteInDto voteInDto, @MappingTarget VoteEntity voteEntity);

    VoteOutDto toDto(VoteEntity vote);
}
