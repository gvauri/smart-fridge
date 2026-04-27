package css.ch.smartfridgejavabackend.notification.services;

import css.ch.smartfridgejavabackend.items.dto.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendWelcomeMail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Willkommen bei SmartFridge!");
        message.setText("Hallo " + name + ",\n\nWillkommen bei SmartFridge!");

        mailSender.send(message);
    }

    @Override
    public void sendExpirationMail(String email, List<ItemResponse> expiringItems) {
        StringBuilder itemsList = new StringBuilder();

        for (ItemResponse item : expiringItems) {
            itemsList.append("- ").append(item.name()).append(" (Ablaufdatum: ").append(item.expirationDate()).append(")\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("SmartFridge - Artikel laufen morgen ab!");
        message.setText("Hallo,\n\nfolgende Artikel in Ihrem Kühlschrank laufen morgen ab:\n\n" + itemsList);

        mailSender.send(message);
    }
}
