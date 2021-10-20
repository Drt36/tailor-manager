package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper{
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserPostDto modelToPostDto(User user);

    UserGetDto modelToGetDto(User user);

    UserUpdateDto modelToUserUpdateDto(User user);

    List<UserGetDto> modelsToGetDtos(List<User> users);

    UserResetPasswordDto modelToUserResetPasswordDto(User user);

    UserChangePasswordDto modelTOUserChangePasswordDto(User user);

    @InheritInverseConfiguration
    User dtoToModel(UserPostDto userPostDto);
}
