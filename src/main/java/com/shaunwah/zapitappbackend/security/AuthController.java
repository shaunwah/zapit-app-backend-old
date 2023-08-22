package com.shaunwah.zapitappbackend.security;

import com.shaunwah.zapitappbackend.misc.utilities.Utilities;
import com.shaunwah.zapitappbackend.security.SecurityTokenService;
import com.shaunwah.zapitappbackend.user.User;
import com.shaunwah.zapitappbackend.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(
        path = "/api/auth",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Log
public class AuthController {
    private final UserService userService;
    private final SecurityTokenService securityTokenService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ofNullable(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(Authentication authentication) {
        log.info("Token requested: %s".formatted(authentication.getName()));
        String token = securityTokenService.generateToken(authentication);
        log.info("Token generated: %s".formatted(token));
        return ResponseEntity.ofNullable(Utilities.generateTokenMessage(token, authentication.getName(), "successfully authenticated").toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ofNullable("kek"); // TODO
    }
}
