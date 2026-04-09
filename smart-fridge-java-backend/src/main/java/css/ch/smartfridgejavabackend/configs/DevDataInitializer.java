package css.ch.smartfridgejavabackend.configs;

import css.ch.smartfridgejavabackend.items.model.Item;
import css.ch.smartfridgejavabackend.items.repository.ItemRepository;
import css.ch.smartfridgejavabackend.items.types.IconType;
import css.ch.smartfridgejavabackend.user.model.User;
import css.ch.smartfridgejavabackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class DevDataInitializer {
    private final UserRepository userRepo;
    private final ItemRepository itemRepo;
    private final PasswordEncoder encoder;

    @Bean
    CommandLineRunner initData() {
        return ignored -> {
            User gottfried = userRepo.findUserByEmail("gottfried@gmail.com")
                    .orElseGet(() -> {
                        User user = new User();
                        user.setName("Gottfried Stutz");
                        user.setEmail("gottfried@gmail.com");
                        user.setPassword(encoder.encode("testtest"));

                        return userRepo.save(user);
                    });

            if (itemRepo.findAll().isEmpty()) {
                Item item1 = new Item();
                item1.setUserId(gottfried.getId());
                item1.setIcon(IconType.MEAT);
                item1.setName("Hackfleisch");
                item1.setDescription("20kg Hackfleisch yallah");
                item1.setBuyingDate(LocalDate.now());
                item1.setExpirationDate(LocalDate.now().plusDays(5));
                item1.setAmount(1);

                itemRepo.save(item1);

                Item item2 = new Item();
                item2.setUserId(gottfried.getId());
                item2.setIcon(IconType.DESSERT);
                item2.setName("Schoggi Yurrrgurrrrt");
                item2.setDescription("Hell yeaaaah - legalize anabolic steroids");
                item2.setBuyingDate(LocalDate.now().minusDays(2));
                item2.setExpirationDate(LocalDate.now().plusDays(4));
                item2.setAmount(20);

                itemRepo.save(item2);
            }
        };
    }
}
