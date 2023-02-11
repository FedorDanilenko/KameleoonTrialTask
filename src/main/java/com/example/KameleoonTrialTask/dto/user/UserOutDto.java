package com.example.KameleoonTrialTask.dto.user;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserOutDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime dataCreated;

}
