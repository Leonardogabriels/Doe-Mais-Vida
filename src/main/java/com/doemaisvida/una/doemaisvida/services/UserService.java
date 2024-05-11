package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entitys.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //public User login (String email, String password){}

    @Transactional
    public void createUser(User obj) {
        if (obj == null) {
            throw new IllegalArgumentException("O objeto User fornecido é nulo");
        }

        boolean userExists = userRepository.existsByEmail(obj.getEmail());
        if (!userExists) {
            userRepository.save(obj);
        } else {
            throw new RuntimeException("Usuário já cadastrado");
        }
    }
}