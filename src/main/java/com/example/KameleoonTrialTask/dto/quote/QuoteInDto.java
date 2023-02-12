package com.example.KameleoonTrialTask.dto.quote;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QuoteInDto {

    @NotBlank
    private String text;

    @NotNull
    private long userId;
}
