package css.ch.smartfridgejavabackend.user.services;


import css.ch.smartfridgejavabackend.auth.dto.SignupRequest;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;


public interface UserService {
    UserResponseDTO createUser(SignupRequest signupRequest);

    UserResponseDTO getUserById(String userId);
}
