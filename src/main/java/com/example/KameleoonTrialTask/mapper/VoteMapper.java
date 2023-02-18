package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.graph.GraphVoteDto;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.Map;

public class VoteMapper {

    public static GraphVoteDto toGraphVoteDto(Map<Timestamp, Integer> map){
        return GraphVoteDto.builder()
                .map(map).build();
    }
}
