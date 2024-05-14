package com.doemaisvida.una.doemaisvida.Dto;

import com.doemaisvida.una.doemaisvida.entitys.User;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;

import java.util.Objects;
import java.util.Optional;

public class UserDto extends User {

    @Id
    private Long id;
    private String name;
    private String bloodType;
    private URL Photograph;

    public UserDto(Optional<User> user) {

    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.bloodType = user.getBloodType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    ///f

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto user = (UserDto) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
