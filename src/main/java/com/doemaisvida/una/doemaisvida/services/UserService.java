package com.doemaisvida.una.doemaisvida.services;

import com.doemaisvida.una.doemaisvida.entities.User;
import com.doemaisvida.una.doemaisvida.repositorys.UserRepository;
import com.doemaisvida.una.doemaisvida.services.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String emailOrPhone, String password) {
        Optional<User> userOptional = userRepository.findByEmail(emailOrPhone);

        if (userOptional.isEmpty()) {
            try {
                Long phone = Long.parseLong(emailOrPhone);
                userOptional = userRepository.findByCellPhone(phone);
            } catch (NumberFormatException e) {
                throw new LoginInvalidException("Usuário não encontrado");
            }
        }

        User user = userOptional.orElseThrow(() -> new LoginInvalidException("Usuário não encontrado"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new InvalidPasswordException("Senha incorreta");
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

        if (userRepository.existsByCellPhone(obj.getCellPhone())) {
            throw new UserAlreadyExistsException("Celular já cadastrado");
        }

        if (!obj.getPassword().equals(obj.getPasswordConfirm())) {
            throw new InvalidPasswordException("As senhas não correspondem");
        }

        String encryptedPassword = passwordEncoder.encode(obj.getPassword());
        obj.setPassword(encryptedPassword);
        obj.setPasswordConfirm(encryptedPassword);

        try {
            return userRepository.save(obj);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao salvar o usuário no banco de dados");
        }
    }

    public User update(Long id, User obj) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            updateData(user, obj);
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado");
        }
    }

    private void updateData(User dataUp, User obj) {
        dataUp.setName(obj.getName());
        if (obj.getEmail() != null && !userRepository.existsByEmail(obj.getEmail())) {
            dataUp.setEmail(obj.getEmail());
        }
        dataUp.setLocation(obj.getLocation());
        dataUp.setImgUrl(obj.getImgUrl());
        if (obj.getCellPhone()!= null && !userRepository.existsByCellPhone(obj.getCellPhone())) {
            dataUp.setCellPhone(obj.getCellPhone());
        }

        if (obj.getPassword() != null && !obj.getPassword().isEmpty() &&
                obj.getPasswordConfirm() != null && !obj.getPasswordConfirm().isEmpty()) {
            if (Objects.equals(obj.getPassword(), obj.getPasswordConfirm())) {
                String encryptedPassword = passwordEncoder.encode(obj.getPassword());
                dataUp.setPassword(encryptedPassword);
                dataUp.setPasswordConfirm(encryptedPassword);
            } else {
                throw new InvalidPasswordException("Senhas não correspondem");
            }
        }
    }
}