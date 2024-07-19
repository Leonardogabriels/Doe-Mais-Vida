package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.Appointment;

import java.util.List;

public interface AppointmentService {

	public Appointment saveAppointment(Appointment appointment);

	public List<Appointment> findAllAppointment();

}
