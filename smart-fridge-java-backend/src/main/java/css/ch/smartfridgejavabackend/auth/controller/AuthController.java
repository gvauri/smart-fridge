package css.ch.smartfridgejavabackend.auth.controller;

import css.ch.smartfridgejavabackend.auth.dto.AuthResponse;
import css.ch.smartfridgejavabackend.auth.dto.LoginRequest;
import css.ch.smartfridgejavabackend.auth.dto.SignupRequest;
import css.ch.smartfridgejavabackend.auth.service.AuthService;
import css.ch.smartfridgejavabackend.auth.service.JwtService;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;
import css.ch.smartfridgejavabackend.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        UserResponseDTO userDto = userService.createUser(signupRequest);
        String token = jwtService.generateToken(userDto.id());

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String userId = authService.authenticate(loginRequest.email(), loginRequest.password());
        String token = jwtService.generateToken(userId);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
