package com.example.KameleoonTrialTask.dto.quote;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuoteDetailDto implements Serializable {
    private Long id;
    private String text;
}
