package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;


@RestController
@RequestMapping(value = "/users")
@Tag(name = "users")
public class UserResource {

    @Autowired
    private UserService userService;



	@Operation(summary = "criar usuario ", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Entrada inválida"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user ){
		userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@Operation(summary = "Atualizar um usuário existente", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Entrada inválida"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user ){
		User newUser = userService.update(id,user);
		return ResponseEntity.ok().body(newUser);
	}
}