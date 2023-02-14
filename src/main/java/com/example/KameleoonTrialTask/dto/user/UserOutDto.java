package com.example.KameleoonTrialTask.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime dataCreated;

}
