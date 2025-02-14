package com.doemaisvida.una.doemaisvida.services.mapper;

import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
import com.doemaisvida.una.doemaisvida.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

	User toUser(UserCreateDTO user);

	UserDTO toUserDTO(User user);
}
