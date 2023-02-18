package com.example.KameleoonTrialTask.dto.quote;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class QuoteDetailDto implements Serializable {
    private Long id;
    private String text;
}
