package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.Hospital;

import java.util.List;

public interface HospitalService {

	public List<Hospital> getHospitalsByCityName(String cityName);

	public Hospital insertHospital(Hospital hospital, String cityName);


}
