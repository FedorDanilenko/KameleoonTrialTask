package com.example.KameleoonTrialTask.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserOutDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime dataCreated;

}
