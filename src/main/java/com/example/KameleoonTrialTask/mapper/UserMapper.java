package com.example.KameleoonTrialTask.mapper;

import com.example.KameleoonTrialTask.dto.user.UserInDto;
import com.example.KameleoonTrialTask.dto.user.UserOutDto;
import com.example.KameleoonTrialTask.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "dataCreated", expression = "java(java.time.LocalDateTime.now())")
    UserEntity toUser(UserInDto userInDto);

    UserOutDto toDto(UserEntity userEntity);

}
