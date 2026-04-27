package css.ch.smartfridgejavabackend.notification;

import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import css.ch.smartfridgejavabackend.items.service.ItemService;
import css.ch.smartfridgejavabackend.notification.services.MailService;
import css.ch.smartfridgejavabackend.user.dto.UserResponseDTO;
import css.ch.smartfridgejavabackend.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExpirationNotification {
    private final ItemService itemService;
    private final MailService mailService;
    private final UserService userService;

    @Scheduled(cron = "0 0 8 * * *")
    public void notifyUsersAboutExpiringItems() {
        List<ItemResponse> expiringItems = itemService.findItemsExpiringTomorrow();

        Map<String, List<ItemResponse>> itemsPerUser = expiringItems.stream()
                .collect(Collectors.groupingBy(ItemResponse::userId));

        itemsPerUser.forEach((userId, items) -> {
            UserResponseDTO user = userService.getUserById(userId);
            mailService.sendExpirationMail(user.email(), items);
        });
    }

}
