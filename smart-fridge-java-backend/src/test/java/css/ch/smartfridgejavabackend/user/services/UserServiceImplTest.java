package css.ch.smartfridgejavabackend.user.services;

import css.ch.smartfridgejavabackend.auth.dto.SignupRequest;
import css.ch.smartfridgejavabackend.exceptions.UserAlreadyExistsException;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;
import css.ch.smartfridgejavabackend.user.mapper.UserMapper;
import css.ch.smartfridgejavabackend.user.model.User;
import css.ch.smartfridgejavabackend.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repo;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void shouldCreateUserWhenEmailDoesNotExist() {
        SignupRequest request = new SignupRequest("Max", "max@example.com", "plain-password");
        User mappedUser = new User();
        mappedUser.setName("Max");
        mappedUser.setEmail("max@example.com");

        User savedUser = new User();
        savedUser.setId("user-1");
        savedUser.setName("Max");
        savedUser.setEmail("max@example.com");
        savedUser.setPassword("encoded-password");

        UserResponseDTO expectedResponse = UserResponseDTO.builder()
                .id("user-1")
                .name("Max")
                .email("max@example.com")
                .build();

        when(repo.existsByEmail("max@example.com")).thenReturn(false);
        when(mapper.map(request)).thenReturn(mappedUser);
        when(passwordEncoder.encode("plain-password")).thenReturn("encoded-password");
        when(repo.save(mappedUser)).thenReturn(savedUser);
        when(mapper.map(savedUser)).thenReturn(expectedResponse);

        UserResponseDTO response = userService.createUser(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(repo).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getPassword()).isEqualTo("encoded-password");
        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    void shouldThrowUserAlreadyExistsWhenEmailAlreadyTaken() {
        SignupRequest request = new SignupRequest("Max", "max@example.com", "plain-password");
        when(repo.existsByEmail("max@example.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(UserAlreadyExistsException.class);

        verify(mapper, never()).map(request);
        verify(passwordEncoder, never()).encode("plain-password");
    }
}
