package ch.bbzw.smartfridgebackend.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignupRequest (
    @field:NotBlank
    private val username: String,
    @field:Email
    private val email: String,
    @field: NotBlank
    @field:Size(min = 8)
    private val password: String
)