package css.ch.smartfridgejavabackend.auth.service;

import css.ch.smartfridgejavabackend.user.model.User;
import css.ch.smartfridgejavabackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(String email, String rawPassword) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Ungültige E-Mail"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Falsches Passwort");
        }

        return user.getId();
    }
}
