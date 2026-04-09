package css.ch.smartfridgejavabackend.auth.service;

public interface AuthService {
    String authenticate(String email, String rawPassword);

}
