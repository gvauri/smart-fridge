package ch.bbzw.smartfridgebackend.user.mapper

import ch.bbzw.smartfridgebackend.auth.dto.SignupRequest
import ch.bbzw.smartfridgebackend.user.dto.UserResponse
import ch.bbzw.smartfridgebackend.user.model.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun map(signupRequest: SignupRequest): User {
        return User(
            name = signupRequest.name,
            email = signupRequest.email,
            password = ""
        )
    }

    fun map(user: User): UserResponse {
        return UserResponse(
            id = user.id ?: "",
            name = user.name,
            email = user.email
        )
    }
}