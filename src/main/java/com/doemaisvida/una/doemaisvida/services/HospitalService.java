package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.entities.Hospital;
import com.doemaisvida.una.doemaisvida.repositorys.CityRepository;
import com.doemaisvida.una.doemaisvida.repositorys.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Hospital> getHospitalsByCityName(String cityName) {
        return hospitalRepository.findByCityName(cityName);
    }
    public Hospital insertHospital(Hospital hospital, String cityName) {
        Optional<City> cityOptional = cityRepository.findByName(cityName);
        if (!cityOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found with name: " + cityName);
        }
        City city = cityOptional.get();
        hospital.setCity(city);
        return hospitalRepository.save(hospital);
    }
}
