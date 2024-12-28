package com.webdev.blog.v1.controller;

import com.webdev.blog.v1.dto.JWTAuthResponse;
import com.webdev.blog.v1.dto.LoginDto;
import com.webdev.blog.v1.dto.RegisterDto;
import com.webdev.blog.v1.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@Tag(
        name = "Auth REST APIs Resource"
)
public class AuthController {

    private AuthService authService;

    @Operation(
            summary = "Login REST API",
            description = "Login REST API is used to authenticate the user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @Parameter(name = "username", description = "String")
    @Parameter(name = "password", description = "String")
    @PostMapping("login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @Operation(
            summary = "Register REST API",
            description = "Register REST API is used to register the user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
