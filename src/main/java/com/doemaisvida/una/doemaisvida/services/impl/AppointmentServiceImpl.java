package com.doemaisvida.una.doemaisvida.services.impl;


import com.doemaisvida.una.doemaisvida.entities.Appointment;
import com.doemaisvida.una.doemaisvida.repositorys.AppointmentRepository;
import com.doemaisvida.una.doemaisvida.services.AppointmentService;
import com.doemaisvida.una.doemaisvida.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("O objeto Appointment não pode ser nulo");
        }

        try {
            return appointmentRepository.save(appointment);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Erro ao salvar o compromisso no banco de dados");
        }
    }

    public List<Appointment> findAllAppointment() {
        try {
            return appointmentRepository.findAll();
        } catch (Exception ex) {
            throw new DatabaseException("Erro ao buscar todos os compromissos no banco de dados");
        }
    }
}
