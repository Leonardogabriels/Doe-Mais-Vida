package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

	public List<City> getAllCities();

	public Optional<City> getCityByName(String name);

	public City insertCity(City city);

}
