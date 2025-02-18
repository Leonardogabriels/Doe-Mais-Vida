package com.doemaisvida.una.doemaisvida.repositorys;

import com.doemaisvida.una.doemaisvida.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByCellPhone(Long phone);
    Optional<User> findByEmail(String email);
}