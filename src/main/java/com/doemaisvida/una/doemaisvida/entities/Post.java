package com.doemaisvida.una.doemaisvida.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "img_post")
	private String imgPost;

	private String description;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "User_id")
	private User user;

	public Post(String imgPost, String description, User user) {
		this.imgPost = imgPost;
		this.description = description;
		this.user = user;
	}

}
