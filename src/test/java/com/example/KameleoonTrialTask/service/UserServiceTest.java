package com.example.KameleoonTrialTask.service;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import com.example.KameleoonTrialTask.exception.AlreadyExistEx;
import com.example.KameleoonTrialTask.mapper.UserMapper;
import com.example.KameleoonTrialTask.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final Long ID = 1L;
    private final UserEntity user = UserEntity.builder()
            .id(ID)
            .name("login")
            .email("email@email.com")
            .password("pass")
            .dataCreated(LocalDateTime.of(1,2,3,4,5))
            .build();
    private final UserInDto userInDto = UserInDto.builder()
            .name("login")
            .email("email@email.com")
            .password("pass")
            .build();
    private final UserOutDto userOutDto = UserOutDto.builder()
            .id(ID)
            .name("login")
            .email("email@email.com")
            .dataCreated(LocalDateTime.of(1,2,3,4,5))
            .build();
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private UserMapper userMapper;

    @Test
    void create() throws AlreadyExistEx {
        Mockito.when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(user);
        Mockito.when((userMapper.toUser(userInDto))).thenReturn(user);
        Mockito.when((userMapper.toDto((user)))).thenReturn(userOutDto);

        UserOutDto userOutDtoResult = userService.create(userInDto);

        assertEquals(ID, userOutDtoResult.getId());
        assertEquals(userInDto.getName(), userOutDtoResult.getName());
        verify(userRepo, times(1)).save(user);
    }
}