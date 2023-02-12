package com.example.KameleoonTrialTask.dto.quote;

import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import lombok.Data;

@Data
public class QuoteOutDto {

    private Long id;
    private String text;
    private UserOutDto user;
}
