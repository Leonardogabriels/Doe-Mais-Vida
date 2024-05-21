package com.doemaisvida.una.doemaisvida.services;


import com.doemaisvida.una.doemaisvida.entities.Post;
import com.doemaisvida.una.doemaisvida.repositorys.PostRepository;
import com.doemaisvida.una.doemaisvida.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> findAll(){
		return postRepository.findAll();
	}

	public Post insert(Post post) {
		if (post == null) {
			throw new IllegalArgumentException("O objeto Post não pode ser nulo");
		}
		return postRepository.save(post);
	}

	public void deletePost(Long postId) {
		if (!postRepository.existsById(postId)) {
			throw new ObjectNotFoundException("Post não encontrado com o ID fornecido: " + postId);
		}
		 postRepository.deleteById(postId);
	}
}
