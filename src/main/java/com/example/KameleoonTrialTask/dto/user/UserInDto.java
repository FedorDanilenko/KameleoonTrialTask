package com.example.KameleoonTrialTask.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Data
public class UserInDto {

    @NotNull
    @NotBlank
    private String name;

    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    @Email
    private String password;
}
