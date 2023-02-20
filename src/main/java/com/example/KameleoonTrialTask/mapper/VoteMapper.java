package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;

@Component
public class VoteMapper {

    public GraphVoteDto toGraphVoteDto(Map<Timestamp, Integer> map){
        return GraphVoteDto.builder()
                .map(map)
                .build();
    }
}
