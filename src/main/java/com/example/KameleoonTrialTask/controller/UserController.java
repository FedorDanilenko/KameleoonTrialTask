package com.example.KameleoonTrialTask.controller;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@Valid @RequestBody UserInDto userInDto) throws AlreadyExistEx {
        userService.create(userInDto);
    }


}
