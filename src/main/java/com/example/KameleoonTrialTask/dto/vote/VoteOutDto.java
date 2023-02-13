package com.example.KameleoonTrialTask.dto.vote;

import com.example.KameleoonTrialTask.entity.VoteType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteOutDto {

    private Long id;
    private VoteType voteType;
    private LocalDateTime dataCreated;
}
