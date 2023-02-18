package com.example.KameleoonTrialTask.dto.graph;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@Data
@Builder
public class GraphVoteDto implements Serializable {
    private Map<Timestamp, Integer> map;
}
