package com.doemaisvida.una.doemaisvida.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String patientName;

    @NotNull
    private LocalDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @NotNull
    private String cpf;

    @NotNull
    private String email;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String phoneNumber;

    private LocalDateTime appointmentTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Appointment(String patientName, LocalDate appointmentDate, Hospital hospital) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", hospital=" + hospital +
                '}';
    }
}