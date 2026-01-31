package com.microservice.auth.service;


import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.entity.User;
import com.microservice.auth.enums.Estado;
import com.microservice.auth.enums.Rol;
import com.microservice.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "El correo ya está registrado";
        }

        User user = User.builder()
                .nombre(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.cliente)
                .estado(Estado.activo)
                .build();

        userRepository.save(user);

        return "Usuario registrado correctamente";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) return "Usuario no existe";

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "Contraseña incorrecta";
        }

        user.setUltimoLogin(LocalDateTime.now());
        userRepository.save(user);

        return "Login exitoso";
    }
}
