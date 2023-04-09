package com.springLongBot.springLongBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SpringLongBot extends TelegramLongPollingBot {

//    private final String botName = System.getenv("botName");
//    private final String botToken = System.getenv("botToken");

    private final String botName = "MyAmazing300323Bot";
    private final String botToken = "6081564544:AAF7UHRn6mgmG8JZa3OIkIC3tzsGQoLFYTM";

    SpringLongBot () {
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Ти пєтушара який щось пише моєму боту");

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
