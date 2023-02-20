package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.mapper.UserMapper;
import com.example.KameleoonTrialTask.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(rollbackOn = {AlreadyExistEx.class, RuntimeException.class})
@Validated
@Builder
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public UserOutDto create(UserInDto userInDto) throws AlreadyExistEx {
        try {
            return userMapper.toDto(userRepo.save(userMapper.toUser(userInDto)));
        } catch (DataAccessException ex) {
            throw new AlreadyExistEx("User with this name already exists");
        }
    }

}
