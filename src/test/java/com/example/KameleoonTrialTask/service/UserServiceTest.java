package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.mapper.UserMapper;
import com.example.KameleoonTrialTask.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Test
    void create() throws AlreadyExistEx {
        UserInDto userInDto = UserInDto.builder()
                .name("login")
                .email("email@email.com")
                .password("pass")
                .build();

        UserOutDto userOutDto = userService.create(userInDto);

        assertNotNull(userOutDto.getId());
        assertEquals("login", userOutDto.getName());
    }

    @Test
    void alreadyEx() {
        UserInDto userInDto = UserInDto.builder()
                .name("login")
                .email("email@email.com")
                .password("pass")
                .build();
        UserEntity user = UserEntity.builder()
                .name("login")
                .email("email2@email.com")
                .password("pass2")
                .build();
        userRepo.deleteAll();
        userRepo.save(user);
        assertThrows(AlreadyExistEx.class, () -> {
            userService.create(userInDto);
        });
    }
}