package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.User;
import com.shaunwah.zapitappbackend.services.SecurityTokenService;
import com.shaunwah.zapitappbackend.services.UserService;
import com.shaunwah.zapitappbackend.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Log
public class AuthController {
    private final UserService userService;
    private final SecurityTokenService securityTokenService;

    @GetMapping("/test")
    public String test() {
        return "hello world!";
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ofNullable(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(Authentication authentication) {
        log.info("Token requested: %s".formatted(authentication.getName()));
        String token = securityTokenService.generateToken(authentication);
        log.info("Token generated: %s".formatted(token));
        return ResponseEntity.ofNullable(Utilities.generateTokenMessage(
                token, "successfully authenticated as %s".formatted(authentication.getName())).toString()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ofNullable("kek"); // TODO
    }
}
