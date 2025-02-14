package com.doemaisvida.una.doemaisvida.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
public class UserDTO {

	private Long id;

	private String name;

	private String email;

	private String bloodType;

	private String location;

	private Long cellPhone;

	private String imgUrl;
}
