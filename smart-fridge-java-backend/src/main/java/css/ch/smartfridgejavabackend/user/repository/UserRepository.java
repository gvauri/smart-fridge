package css.ch.smartfridgejavabackend.user.repository;

import css.ch.smartfridgejavabackend.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
