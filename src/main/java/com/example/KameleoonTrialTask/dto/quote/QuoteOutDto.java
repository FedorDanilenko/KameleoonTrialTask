package com.example.KameleoonTrialTask.dto.quote;

import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteOutDto {

    private Long id;
    private String text;
    private Long score;
}
