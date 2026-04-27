package css.ch.smartfridgejavabackend.notification.services;

import css.ch.smartfridgejavabackend.items.dto.ItemResponse;

import java.util.List;

public interface MailService {

    void sendWelcomeMail(String to, String name);

    void sendExpirationMail(String email, List<ItemResponse> expiringItems);
}
