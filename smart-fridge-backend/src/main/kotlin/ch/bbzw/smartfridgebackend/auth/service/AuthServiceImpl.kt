package ch.bbzw.smartfridgebackend.auth.service

import ch.bbzw.smartfridgebackend.user.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthService {

    override fun authenticate(email: String, rawPassword: String): String? {
        val user = userRepository.findUserByEmail(email).orElseThrow { BadCredentialsException("Ungültige E-Mail") }

        if (!passwordEncoder.matches(rawPassword, user.password)) {
            throw BadCredentialsException("Falsches Passwort")
        }

        return user.id
    }
}