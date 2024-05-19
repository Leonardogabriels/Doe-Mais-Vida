package com.doemaisvida.una.doemaisvida.repositorys;

import com.doemaisvida.una.doemaisvida.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
