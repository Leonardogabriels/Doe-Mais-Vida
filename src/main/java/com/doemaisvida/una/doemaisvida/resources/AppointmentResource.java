package com.doemaisvida.una.doemaisvida.resources;


import com.doemaisvida.una.doemaisvida.entities.Appointment;
import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.entities.Hospital;
import com.doemaisvida.una.doemaisvida.services.AppointmentService;
import com.doemaisvida.una.doemaisvida.services.CityService;
import com.doemaisvida.una.doemaisvida.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping(value = "/cities")
    public City createCities(@RequestBody City obj) {
        return cityService.insertCity(obj);
    }

    @GetMapping(value = "/hospitals")
    public List<Hospital> getHospitalsByCity(@RequestParam String cityName) {
        return hospitalService.getHospitalsByCityName(cityName);
    }

    @PostMapping(value = "/hospitals")
    public Hospital createHospital(@RequestBody Hospital hospital, @RequestParam String cityName) {
        return hospitalService.insertHospital(hospital, cityName);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }


}
