package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;

import java.sql.Timestamp;
import java.util.Map;

public class VoteMapper {

    public GraphVoteDto toGraphVoteDto(Map<Timestamp, Integer> map){
        return GraphVoteDto.builder()
                .map(map)
                .build();
    }
}
