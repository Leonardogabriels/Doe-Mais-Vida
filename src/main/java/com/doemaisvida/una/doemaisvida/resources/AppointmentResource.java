package com.doemaisvida.una.doemaisvida.resources;

import com.doemaisvida.una.doemaisvida.entities.Appointment;
import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.entities.Hospital;
import com.doemaisvida.una.doemaisvida.services.AppointmentService;
import com.doemaisvida.una.doemaisvida.services.CityService;
import com.doemaisvida.una.doemaisvida.services.HospitalService;
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
    private CityService cityService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/cities")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok().body(cities);
    }

    @PostMapping(value = "/cities")
    public ResponseEntity<City> createCities(@RequestBody City obj) {
        City createdCity = cityService.insertCity(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @GetMapping(value = "/hospitals")
    public ResponseEntity<List<Hospital>> getHospitalsByCity(@RequestParam String cityName) {
        List<Hospital> hospitals = hospitalService.getHospitalsByCityName(cityName);
        return ResponseEntity.ok().body(hospitals);
    }

    @PostMapping(value = "/hospitals")
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

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.saveAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> findAllAppointment(){
        List<Appointment> appointments = appointmentService.findAllAppointment();
        return ResponseEntity.ok().body(appointments);
    }
}
