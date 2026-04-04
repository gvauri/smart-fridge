package ch.bbzw.smartfridgebackend.user.repository

import ch.bbzw.smartfridgebackend.user.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface UserRepository : MongoRepository<User, String> {
    fun findUserByEmail(email: String): Optional<User>

    fun searchById(id: String?): Optional<User>

    fun existsByEmail(email: String): Boolean
}
