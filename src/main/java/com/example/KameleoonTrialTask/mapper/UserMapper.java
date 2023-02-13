package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "dataCreated", expression = "java(java.time.LocalDateTime.now())")
    UserEntity toUser(UserInDto userInDto);

    UserOutDto toDto(UserEntity userEntity);

}
