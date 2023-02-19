package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.QuoteEntity;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

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


    @InjectMocks
    private UserMapper userMapper;

    @Test
    void toUser() {
        UserEntity userResult = userMapper.toUser(userInDto);

        assertEquals("login", userResult.getName());
        assertEquals("pass", userResult.getPassword());
        assertEquals("email@email.com", userResult.getEmail());
    }

    @Test
    void toDto() {
        UserOutDto outDtoResult = userMapper.toDto(user);

        assertEquals(ID, outDtoResult.getId());
        assertEquals("login", outDtoResult.getName());
        assertEquals("email@email.com", outDtoResult.getEmail());
        assertEquals(LocalDateTime.of(1,2,3,4,5), outDtoResult.getDataCreated());
    }
}