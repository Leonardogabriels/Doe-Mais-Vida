package com.doemaisvida.una.doemaisvida.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
