package com.doemaisvida.una.doemaisvida.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserCreateDTO {


	@NotNull
	private String name;

	@Email
	private String email;

	@Size(min = 6, max = 255)
	private String password;

	@Size(min = 6, max = 255)
	private String passwordConfirm;

	private String bloodType;

	private String location;

	@NotNull
	@Digits(integer = 15, fraction = 0)
	private Long cellPhone;

	private String imgUrl;

}

