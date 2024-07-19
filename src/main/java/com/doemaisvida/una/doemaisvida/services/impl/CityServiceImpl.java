package com.doemaisvida.una.doemaisvida.services.impl;

import com.doemaisvida.una.doemaisvida.entities.City;
import com.doemaisvida.una.doemaisvida.repositorys.CityRepository;
import com.doemaisvida.una.doemaisvida.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    public City insertCity(City city){
        return  cityRepository.save(city);
    }
}
