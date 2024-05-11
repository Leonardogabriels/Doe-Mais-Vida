package com.doemaisvida.una.doemaisvida.repositorys;

import com.doemaisvida.una.doemaisvida.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}