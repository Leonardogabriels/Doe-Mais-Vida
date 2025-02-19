package com.doemaisvida.una.doemaisvida.Controller;


import com.doemaisvida.una.doemaisvida.DTO.UserCreateDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserDTO;
import com.doemaisvida.una.doemaisvida.DTO.UserUpdateDTO;
import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.services.TokenService;
import com.doemaisvida.una.doemaisvida.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/users")
@Tag(name = "users")
public class UserController {


    private final UserService userService;
	private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
		this.tokenService = tokenService;
	}


    @Operation(summary = "criar usuario ", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Entrada inválida"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO user ){
		var userDto = userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(userDto.getId()).toUri();
		return ResponseEntity.created(uri).body(userDto);
	}

	@Operation(summary = "Atualizar um usuário existente", method = "PUT")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Entrada inválida"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PutMapping
	public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String token,
											  @RequestBody @Valid UserUpdateDTO userDto) {
		User user = tokenService.getUserFromToken(token);
		UserDTO updatedUser = userService.update(user, userDto);
		return ResponseEntity.ok(updatedUser);
	}

	//Criar metodo de atualização de senha
}