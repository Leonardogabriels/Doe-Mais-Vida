package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entitys.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import com.doemaisvida.una.doemaisvida.services.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (Objects.equals(user.getPassword(), password)) {
                return user;
            } else {
                throw new InvalidPasswordException("Senha incorreta");
            }
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional
    public User createUser(User obj) {
        if (obj == null) {
            throw new IllegalArgumentException("O objeto User fornecido é nulo");
        }

        if (userRepository.existsByEmail(obj.getEmail())) {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }

        if (!obj.getPassword().equals(obj.getPasswordConfirm())) {
            throw new InvalidPasswordException("As senhas não coicidem");
        }

        try {
            return userRepository.save(obj);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar o usuário no banco de dados");
        }
    }
}