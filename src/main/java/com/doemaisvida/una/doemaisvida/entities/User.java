package com.doemaisvida.una.doemaisvida.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String email;

	private String password;

	private String passwordConfirm;

	@Column(name = "blood_type")
	private String bloodType;

	private String location;

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
