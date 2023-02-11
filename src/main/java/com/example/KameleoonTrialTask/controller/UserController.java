package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.UserAlreadyExistEx;
import com.example.KameleoonTrialTask.repository.UserRepo;
import com.example.KameleoonTrialTask.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Data
public class UserController {

    @Autowired
    private UserService userService;

    public void createUser(@Valid @RequestBody UserInDto userInDto) throws UserAlreadyExistEx {
        userService.create(userInDto);
    }


}
