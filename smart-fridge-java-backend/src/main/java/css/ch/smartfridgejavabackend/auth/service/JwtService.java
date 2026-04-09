package css.ch.smartfridgejavabackend.auth.service;


import javax.crypto.SecretKey;

public interface JwtService {
    String generateToken(String userId);

    SecretKey getKey();
}
