package css.ch.smartfridgejavabackend.items.repository;

import css.ch.smartfridgejavabackend.items.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByUserId(String userId);

    List<Item> findByExpirationDate(LocalDate expirationDate);
}
