package com.internship.tailormanager.mapper;

import com.internship.tailormanager.dto.UserDto;
import com.internship.tailormanager.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto modelToDto(User user);

    List<UserDto> modelsToDtos(List<User> users);

    @InheritInverseConfiguration
    User dtoToModel(UserDto userDto);
}
