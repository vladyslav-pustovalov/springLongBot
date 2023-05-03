package com.sprint_long_bot.service;
/**
 * @author Vladyslav Pustovalov
 * class TelegramBot which gets messages from useers and could answer to them
 */

import com.sprint_long_bot.config.BotConfig;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringLongBot extends TelegramLongPollingBot {

    final BotConfig config = new BotConfig();

    final MessageSender messageSender = new MessageSender();

    public SpringLongBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    /**
     * Method which gets updates from users and sends to them an answer
     */
    @Override
    public void onUpdateReceived(Update update) {
        String userName = update.getMessage().getChat().getUserName();
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        String errorOccurred = "Error occurred: ";

        if (!update.hasMessage() || !update.getMessage().hasText()) {
            log.warn("Unexpected update from user " + userName);
            try {
                execute(messageSender.warnMessage(chatId));
                log.info("Warn message was sent to user " + userName);
            } catch (TelegramApiException e) {
                log.error(errorOccurred + e);
            }
        } else {

            switch (messageText) {
                case "/start" -> {
                    try {
                        execute(messageSender.startMessage(chatId));
                        log.info("Welcome message was sent to user " + userName);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred + e);
                    }
                }

                case "My subscription list" -> {
                    try {
                        execute(messageSender.userSubscriptionsList(chatId));
                        log.info("Subscription list was sent to user " + userName);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred + e);
                    }
                }

                case "Available tender sites for subscription" -> {
                    try {
                        execute(messageSender.availableSitesList(chatId));
                        log.info("Available sites list was sent to user " + userName);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred + e);
                    }
                }

                case "Help instructions" -> {
                    try {
                        execute(messageSender.helpMessage(chatId));
                        log.info("Help instructions was sent to user " + userName);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred + e);
                    }
                }

                default -> {
                    try {
                        execute(messageSender.defaultMessage(chatId));
                        log.info("Default message was sent to user " + userName);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred + e);
                    }
                }
            }
        }
    }
}
