package com.microservice.auth.service;


import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.entity.User;
import com.microservice.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "El correo ya está registrado";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword()) // luego se cifra
                .role("USER")
                .enabled(true)
                .build();

        userRepository.save(user);

        return "Usuario registrado correctamente";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) return "Usuario no existe";

        if (!user.getPassword().equals(request.getPassword())) {
            return "Contraseña incorrecta";
        }

        return "Login exitoso (JWT se implementará luego)";
    }
}