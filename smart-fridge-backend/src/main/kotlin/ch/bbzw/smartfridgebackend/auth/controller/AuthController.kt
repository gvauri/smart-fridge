package ch.bbzw.smartfridgebackend.auth.controller

import ch.bbzw.smartfridgebackend.auth.dto.AuthResponse
import ch.bbzw.smartfridgebackend.auth.dto.LoginRequest
import ch.bbzw.smartfridgebackend.auth.dto.SignupRequest
import ch.bbzw.smartfridgebackend.auth.service.AuthService
import ch.bbzw.smartfridgebackend.auth.service.JwtService
import ch.bbzw.smartfridgebackend.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signupRequest: SignupRequest): ResponseEntity<AuthResponse> {
        val userDto = userService.createUser(signupRequest)
        val token = jwtService.generateToken(userDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(AuthResponse(token))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<AuthResponse> {
        val userId = authService.authenticate(loginRequest.email, loginRequest.password)
        val userDTO = userService.getUserById(userId)
        val token = jwtService.generateToken(userDTO)

        return ResponseEntity.ok(AuthResponse(token))
    }
}