package ch.bbzw.smartfridgebackend.user.service

import ch.bbzw.smartfridgebackend.auth.dto.SignupRequest
import ch.bbzw.smartfridgebackend.user.dto.UserResponse
import ch.bbzw.smartfridgebackend.exceptions.UserAlreadyExistsException
import ch.bbzw.smartfridgebackend.exceptions.UserNotFoundException
import ch.bbzw.smartfridgebackend.user.mapper.UserMapper
import ch.bbzw.smartfridgebackend.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val mapper: UserMapper
) : UserService {

    override fun createUser(signupRequest: SignupRequest): UserResponse {
        if (userRepository.existsByEmail(signupRequest.email)) {
            throw UserAlreadyExistsException("User mit email ${signupRequest.email} existiert bereits")
        }

        var user = mapper.map(signupRequest)

        user = user.copy(
            password = passwordEncoder.encode(signupRequest.password)
        )

        return mapper.map(userRepository.save(user))
    }

    override fun getUserById(id: String): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("User not found with id $id") }

        return mapper.map(user)
    }
}
