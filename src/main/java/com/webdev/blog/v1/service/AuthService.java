package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
