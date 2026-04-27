package css.ch.smartfridgejavabackend.user.services;

import css.ch.smartfridgejavabackend.auth.dto.SignupRequest;
import css.ch.smartfridgejavabackend.exceptions.UserAlreadyExistsException;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;
import css.ch.smartfridgejavabackend.user.mapper.UserMapper;
import css.ch.smartfridgejavabackend.user.model.User;
import css.ch.smartfridgejavabackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(SignupRequest request) {
        if (repo.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("User mit email " + request.email() + " existiert bereits");
        }

        User user = mapper.map(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        return mapper.map(repo.save(user));
    }

    @Override
    public UserResponseDTO getUserById(String userId) {
        return repo.findById(userId)
                .map(mapper::map)
                .orElseThrow(() -> new RuntimeException("User mit ID " + userId + " nicht gefunden"));
    }
}
