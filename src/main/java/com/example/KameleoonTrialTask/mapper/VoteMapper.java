package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.Map;

@Mapper
public interface VoteMapper {

    GraphVoteDto toGraphVoteDto(Map<Timestamp, Integer> map);
}
