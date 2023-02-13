package com.example.KameleoonTrialTask.entity;

import java.util.Arrays;
import java.util.Optional;

public enum SortType {

    TOP,

    FLOP;

    public static Optional<SortType> from(String stringType) {
        return Arrays.stream(values()).filter(s1 -> s1.name().equalsIgnoreCase(stringType)).findFirst();
    }
}
