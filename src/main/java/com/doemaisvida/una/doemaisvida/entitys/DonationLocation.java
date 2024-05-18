package com.doemaisvida.una.doemaisvida.entitys;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class DonationLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String town;

    @NotNull
    @Size(min = 1, max = 100)
    private String serviceUnit;

    @NotNull
    private LocalDateTime dateTimeService;

    public String getString() {
        return town;
    }

    public void setString(String town) {
        this.town = town;
    }

    public String getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(String serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public LocalDateTime getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(LocalDateTime dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
}
