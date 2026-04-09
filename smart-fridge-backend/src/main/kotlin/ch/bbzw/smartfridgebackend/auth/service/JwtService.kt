package ch.bbzw.smartfridgebackend.auth.service

import ch.bbzw.smartfridgebackend.user.dto.UserResponse
import javax.crypto.SecretKey

interface JwtService {
    fun generateToken(user: UserResponse): String

    fun getKey(): SecretKey
}