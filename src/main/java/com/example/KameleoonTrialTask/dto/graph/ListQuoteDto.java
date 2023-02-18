package com.example.KameleoonTrialTask.dto.graph;

import com.example.KameleoonTrialTask.dto.quote.QuoteOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ListQuoteDto implements Serializable {
    private List<QuoteEntity> quotes;
}
