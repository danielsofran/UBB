package com.example.mobilebackend1.controller;

import com.example.mobilebackend1.dto.UserDto;
import com.example.mobilebackend1.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final JwtService jwtService;

    @GetMapping("/me")
    @CrossOrigin(origins = {"http://localhost:8100"}, allowedHeaders = "*")
    public UserDto getUser(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return jwtService.getUserFromToken(token);
        }
        return null;
    }
}
