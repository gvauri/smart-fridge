package css.ch.smartfridgejavabackend.auth.service;

import css.ch.smartfridgejavabackend.user.model.User;
import css.ch.smartfridgejavabackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldReturnUserIdWhenCredentialsAreValid() {
        User user = new User();
        user.setId("user-123");
        user.setEmail("user@example.com");
        user.setPassword("encoded-password");

        when(userRepository.findUserByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plain-password", "encoded-password")).thenReturn(true);

        String userId = authService.authenticate("user@example.com", "plain-password");

        assertThat(userId).isEqualTo("user-123");
        verify(passwordEncoder).matches("plain-password", "encoded-password");
    }

    @Test
    void shouldThrowBadCredentialsWhenEmailDoesNotExist() {
        when(userRepository.findUserByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.authenticate("missing@example.com", "plain-password"))
                .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    void shouldThrowBadCredentialsWhenPasswordIsInvalid() {
        User user = new User();
        user.setId("user-123");
        user.setEmail("user@example.com");
        user.setPassword("encoded-password");

        when(userRepository.findUserByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "encoded-password")).thenReturn(false);

        assertThatThrownBy(() -> authService.authenticate("user@example.com", "wrong-password"))
                .isInstanceOf(BadCredentialsException.class);
    }
}
