package com.doemaisvida.una.doemaisvida.Controller;

import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	public CityController(CityService cityService){
		this.cityService = cityService;
	}

	@Operation(summary = "Buscar todas as cidades", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de cidades recuperada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@GetMapping
	public ResponseEntity<List<City>> getAllCities() {
		List<City> cities = cityService.getAllCities();
		return ResponseEntity.ok().body(cities);
	}

	@Operation(summary = "Criar uma nova cidade", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cidade criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "inserção ruim"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PostMapping
	public ResponseEntity<City> createCities(@RequestBody City obj) {
		City createdCity = cityService.insertCity(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
	}
}
