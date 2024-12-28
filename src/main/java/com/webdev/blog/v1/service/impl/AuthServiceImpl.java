package com.webdev.blog.v1.service.impl;

import com.webdev.blog.v1.dto.LoginDto;
import com.webdev.blog.v1.dto.RegisterDto;
import com.webdev.blog.v1.entity.Role;
import com.webdev.blog.v1.entity.User;
import com.webdev.blog.v1.exception.BlogApiException;
import com.webdev.blog.v1.exception.ResourceNotFoundException;
import com.webdev.blog.v1.repostitory.RoleRepository;
import com.webdev.blog.v1.repostitory.UserRepository;
import com.webdev.blog.v1.security.JwtTokenProvider;
import com.webdev.blog.v1.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
//        Проверяем существует ли пользователь в БД
        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApiException("Пользователь с таким именем уже существует");
        }

//        Проверка на наличие электронной почты в БД
        if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException("Этот адрес электронной почты уже используется");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new ResourceNotFoundException("Такая рольв Базе данных не обнаружена")
        );
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);
        return "Пользователь успешно зарегистрирован";
    }
}
