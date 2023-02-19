package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UserMapper {

    public UserEntity toUser(UserInDto userInDto) {
        return UserEntity.builder()
                .name(userInDto.getName())
                .email(userInDto.getEmail())
                .password(userInDto.getPassword())
                .build();
    }

    public UserOutDto toDto(UserEntity userEntity) {
        return UserOutDto.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .dataCreated(userEntity.getDataCreated())
                    .build();
        }

}
