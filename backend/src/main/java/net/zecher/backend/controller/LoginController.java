package net.zecher.backend.controller;

import net.zecher.backend.dto.AuthDto;
import net.zecher.backend.security.JwtService;
import net.zecher.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginController(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody AuthDto authDto) {
        // Https should be used
        if (authDto.getUserName() == null || authDto.getUserName().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        var result = userService.registerUser(authDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        // Https should be used
        if (authDto.getUserName() == null || authDto.getUserName().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(authDto.getUserName(), authDto.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            var jwt = jwtService.generateToken(authDto.getUserName());
            return ResponseEntity.ok(jwt);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
