package com.doemaisvida.una.doemaisvida.entities;


import jakarta.persistence.*;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "img_post")
	private String imgPost;

	private String description;

	@ManyToOne
	private User user;

	public Post() {
	}

	public Post(String imgPost, String description, User user) {
		this.imgPost = imgPost;
		this.description = description;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgPost() {
		return imgPost;
	}

	public void setImgPost(String imgPost) {
		this.imgPost = imgPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
