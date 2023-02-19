package com.example.KameleoonTrialTask.dto.quote;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
public class QuoteInDto {

    @NotBlank
    private String text;

    @NotNull
    private Long userId;
}
