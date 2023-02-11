package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.exception.UserAlreadyExistEx;
import com.example.KameleoonTrialTask.mapper.UserMapper;
import com.example.KameleoonTrialTask.repository.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private UserMapper userMapper;

    private UserOutDto create(UserInDto userInDto) throws UserAlreadyExistEx {
        try {
            return userMapper.toDto(userRepo.save(userMapper.toUser(userInDto)));
        } catch (DataAccessException ex) {
            throw new UserAlreadyExistEx("User with this name already exists");
        }
    }


}
