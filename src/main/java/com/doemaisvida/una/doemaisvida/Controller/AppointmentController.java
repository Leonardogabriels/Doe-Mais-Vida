package com.doemaisvida.una.doemaisvida.resources;

import com.doemaisvida.una.doemaisvida.entities.Appointment;
import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.entities.Hospital;
import com.doemaisvida.una.doemaisvida.services.AppointmentService;
import com.doemaisvida.una.doemaisvida.services.CityService;
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
@RequestMapping(value = "/appointment")
public class AppointmentResource {


    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary = "Cria uma agendamento  ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "inserção ruim"),
            @ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
    })
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @Operation(summary = "busca todos os agendamentos  ", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "agendamento recuperados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro do Servidor Interno")
    })
    @GetMapping
    public ResponseEntity<List<Appointment>> findAllAppointment(){
        List<Appointment> appointments = appointmentService.findAllAppointment();
        return ResponseEntity.ok().body(appointments);
    }
}
