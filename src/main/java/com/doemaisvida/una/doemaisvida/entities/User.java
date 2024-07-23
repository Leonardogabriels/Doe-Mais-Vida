package com.doemaisvida.una.doemaisvida.entities;

import com.doemaisvida.una.doemaisvida.entities.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 50)
	private String name;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Size(min = 6, max = 255)
	private String password;

	@NotNull
	@Size(min = 6, max = 255)
	@Column(name = "password_confirm")
	private String passwordConfirm;

	@Column(name = "blood_type")
	private String bloodType;

	private String location;

	@NotNull
	@Digits(integer = 15, fraction = 0)
	@Column(name = "cell_phone")
	private Long cellPhone;

	@Column(name = "img_url")
	private String imgUrl;


	public User(Long id, String name, String email, String password, String passwordConfirm,
				String bloodType, String location, Long cellPhone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.bloodType = bloodType;
		this.location = location;
		this.cellPhone = cellPhone;
	}

}
