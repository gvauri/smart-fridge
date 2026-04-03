package ch.bbzw.smartfridgebackend.user.service

import ch.bbzw.smartfridgebackend.auth.dto.SignupRequest
import ch.bbzw.smartfridgebackend.user.dto.UserResponse

interface UserService {
    fun createUser(signupRequest: SignupRequest): UserResponse

    fun getUserById(id: String): UserResponse
}
