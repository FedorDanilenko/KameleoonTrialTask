package com.example.KameleoonTrialTask.dto.graph;

import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Map;

@Data
public class GraphVoteDto implements Serializable {
    private Map<Timestamp, Integer> map;
}
