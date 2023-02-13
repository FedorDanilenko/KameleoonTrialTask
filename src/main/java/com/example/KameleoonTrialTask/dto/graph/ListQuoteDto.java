package com.example.KameleoonTrialTask.dto.graph;

import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ListQuoteDto implements Serializable {
    private List<QuoteOutDto> quotes;
}
