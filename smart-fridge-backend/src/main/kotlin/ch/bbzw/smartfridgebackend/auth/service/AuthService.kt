package ch.bbzw.smartfridgebackend.auth.service

interface AuthService {
    fun authenticate(email: String, rawPassword: String): String
}