package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.entities.Post;
import com.doemaisvida.una.doemaisvida.services.PostService;
import com.doemaisvida.una.doemaisvida.services.exceptions.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
@Tag(name = "posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@Operation(summary = "Buscar todas as postagens  ", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Postado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> posts = postService.findAll();
		return ResponseEntity.ok().body(posts);
	}

	@Operation(summary = "Insira uma nova postagem  ", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Postagem criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody Post obj) {
		ResponseEntity<Post> response;
		Post insertedPost = postService.insert(obj);

		return ResponseEntity.status(HttpStatus.CREATED).body(insertedPost);
	}

	@Operation(summary = "Excluir uma postagem por ID", method = "DELETE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Postagem deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "postagem não encontreada"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@DeleteMapping
	public ResponseEntity<Void> deletePost(Long postId) {

		postService.deletePost(postId);
		return ResponseEntity.noContent().build();
	}


}
