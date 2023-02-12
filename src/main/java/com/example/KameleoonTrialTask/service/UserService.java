package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.exception.UserAlreadyExistEx;
import com.example.KameleoonTrialTask.mapper.UserMapper;
import com.example.KameleoonTrialTask.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Data
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private UserMapper userMapper;

    public UserOutDto create(UserInDto userInDto) throws UserAlreadyExistEx {
        try {
            return userMapper.toDto(userRepo.save(userMapper.toUser(userInDto)));
        } catch (Exception ex) {
            throw new UserAlreadyExistEx("User with this name already exists");
        }
    }


}
