package com.doemaisvida.una.doemaisvida.Controller;

import com.doemaisvida.una.doemaisvida.DTO.AuthenticationDTO;
import com.doemaisvida.una.doemaisvida.services.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	private AuthServiceImpl authService;

	@Operation(summary = "login usando celular ou e-mail e senha", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Entrada inválida"),
			@ApiResponse(responseCode = "401", description = "Entrada não autorizada"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto){
		return ResponseEntity.ok(authService.login(authDto));
	}
}
