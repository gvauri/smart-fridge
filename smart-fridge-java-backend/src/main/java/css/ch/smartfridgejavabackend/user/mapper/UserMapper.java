package css.ch.smartfridgejavabackend.user.mapper;

import css.ch.smartfridgejavabackend.auth.dto.SignupRequest;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;
import css.ch.smartfridgejavabackend.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(SignupRequest signupRequest) {
        if (signupRequest == null) {
            throw new IllegalArgumentException("SignupRequest darf nicht null sein");
        }

        User user = new User();
        user.setName(signupRequest.name());
        user.setEmail(signupRequest.email());
        return user;
    }

    public UserResponseDTO map(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User darf nicht null sein");
        }

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
