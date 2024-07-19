package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.Post;

import java.util.List;

public interface PostService {

	public List<Post> findAll();

	public Post insert(Post post);

	public void deletePost(Long postId);

}
