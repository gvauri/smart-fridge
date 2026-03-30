package ch.bbzw.smartfridgebackend.auth.controller

import ch.bbzw.smartfridgebackend.auth.dto.AuthResponse
import ch.bbzw.smartfridgebackend.auth.dto.LoginRequest
import ch.bbzw.smartfridgebackend.auth.dto.SignupRequest
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController {

    @PostMapping("/login")
    fun login(@Valid @RequestBody login: LoginRequest): AuthResponse {
        return ResponseEntity.ok()
    }

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signup: SignupRequest): AuthResponse {
        return ResponseEntity.ok()
    }
}