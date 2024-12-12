package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.LoginDto;
import com.webdev.blog.v1.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
