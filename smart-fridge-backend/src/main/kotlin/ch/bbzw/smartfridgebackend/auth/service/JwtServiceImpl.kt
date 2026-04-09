package ch.bbzw.smartfridgebackend.auth.service

import ch.bbzw.smartfridgebackend.user.dto.UserResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtServiceImpl(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val expirationMs: Long
) : JwtService {

    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

    override fun generateToken(user: UserResponse): String {
        return Jwts.builder()
            .subject(user.id)
            .claim("email", user.email)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(key)
            .compact()
    }

    override fun getKey(): SecretKey = key
}