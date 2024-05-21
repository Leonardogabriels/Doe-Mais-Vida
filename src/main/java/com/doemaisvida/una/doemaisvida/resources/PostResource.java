package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.entities.Post;
import com.doemaisvida.una.doemaisvida.services.PostService;
import com.doemaisvida.una.doemaisvida.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> posts = postService.findAll();
		return ResponseEntity.ok().body(posts);
	}

	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody Post obj) {
		ResponseEntity<Post> response;
		Post insertedPost = postService.insert(obj);

		return ResponseEntity.status(HttpStatus.CREATED).body(insertedPost);
	}

	@DeleteMapping
	public ResponseEntity<Void> deletePost(Long postId) {

		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}


}
