package css.ch.smartfridgejavabackend.items.model;

import css.ch.smartfridgejavabackend.items.types.IconType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "items")
@TypeAlias("item")
public class Item {
    @Id
    private String id;
    private String userId;
    private IconType icon;
    private String name;
    private String description;
    private LocalDate buyingDate;
    private LocalDate expirationDate;
    private int amount;
}
