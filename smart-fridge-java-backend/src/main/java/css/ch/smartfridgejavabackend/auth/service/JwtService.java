package css.ch.smartfridgejavabackend.auth.service;


import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;

import javax.crypto.SecretKey;

public interface JwtService {
    String generateToken(String userId);

    SecretKey getKey();
}
