package com.doemaisvida.una.doemaisvida.services.mapper;

import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
import com.doemaisvida.una.doemaisvida.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Component
public interface UserMapper {

	User toUser(UserCreateDTO user);

	UserDTO toUserDTO(User user);
}
