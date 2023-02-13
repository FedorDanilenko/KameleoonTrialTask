package com.example.KameleoonTrialTask.dto.vote;

import com.sun.jdi.VoidType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VoteInDto {

    @NotNull
    private Long id;
    private VoidType voidType;
}
