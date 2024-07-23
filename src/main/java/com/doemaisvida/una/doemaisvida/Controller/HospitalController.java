package com.doemaisvida.una.doemaisvida.Controller;

import com.doemaisvida.una.doemaisvida.entities.Hospital;
import com.doemaisvida.una.doemaisvida.services.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	public HospitalController(HospitalService hospitalService){
		this.hospitalService = hospitalService;
	}

	@Operation(summary = "Buscar todas os Hospitais pelo nome da cidade ", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de hospitais recuperada com sucesso"),
			@ApiResponse(responseCode = "400", description = "inserção ruim"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@GetMapping
	public ResponseEntity<List<Hospital>> getHospitalsByCity(@RequestParam String cityName) {
		List<Hospital> hospitals = hospitalService.getHospitalsByCityName(cityName);
		return ResponseEntity.ok().body(hospitals);
	}

	@Operation(summary = "Cria um Hospital,com o id ou nome da cidade ", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "hospitais criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "inserção ruim"),
			@ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
	})
	@PostMapping
	public ResponseEntity<Hospital> createHospital(@RequestBody Map<String, Object> payload) {
		if (!payload.containsKey("name") || !payload.containsKey("cityName")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falta 'nome' ou 'cityName' na requisição");
		}

		String hospitalName = (String) payload.get("name");
		String cityName = (String) payload.get("cityName");

		Hospital hospital = new Hospital();
		hospital.setName(hospitalName);

		Hospital createdHospital = hospitalService.insertHospital(hospital, cityName);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdHospital);
	}

}
