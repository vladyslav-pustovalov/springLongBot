package com.springLongBot.springLongBot.service;
/**
 * @author Vladyslav Pustovalov
 */

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringLongBot extends TelegramLongPollingBot {

    final String botName = System.getenv("botName");

    final String errorOccurred = "Error occurred: ";

    public SpringLongBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    /**
     * Method which gets updates from a user and sends to they an answer
     */
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> {

                    try {
                        sendStartMessage(chatId);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred+e);
                    }
                }

                case "My subscription list" -> {
                    try {
                        sendSubscriptionList(chatId);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred+e);
                    }
                }

                case "Available tender sites for subscription" -> {
                    try {
                        sendAvailableSitesList(chatId);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred+e);
                    }
                }

                case "Help instructions" -> {
                    try {
                        sendHelpMessage(chatId);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred+e);
                    }
                }

                default ->{
                    try {
                        sendDefaultMessage(chatId);
                    } catch (TelegramApiException e) {
                        log.error(errorOccurred+e);
                    }
                }

            }
        }
    }

    /**
     * Method which sends the welcome text to a user
     */
    void sendStartMessage(long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Welcome text");
        message.setReplyMarkup(setKeyboard());

        execute(message);
    }

    /**
     * Method which sends to a user the list with they subscription
     */
    void sendSubscriptionList(long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("User's subscription list");
        message.setReplyMarkup(setKeyboard());

        execute(message);

    }

    /**
     * Method which sends the list with tender sites available for the subscription
     */
    void sendAvailableSitesList(long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Available sites for subscription");
        message.setReplyMarkup(setKeyboard());

        execute(message);
    }

    /**
     * Method which sends information and instructions for a user
     */
    void sendHelpMessage (long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Help text");
        message.setReplyMarkup(setKeyboard());

        execute(message);
    }

    /**
     * Method which sends default answer on not supported commands
     */
    void sendDefaultMessage(long chatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Sorry, this command is not supported!\nPlease use the Keyboard buttons!");
        message.setReplyMarkup(setKeyboard());

        execute(message);
    }

    /**
     * Method which initializes default keyboard for the bot
     */
    ReplyKeyboardMarkup setKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("My subscription list");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("Available tender sites for subscription");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("Help instructions");

        keyboardRows.add(row1);
        keyboardRows.add(row2);
        keyboardRows.add(row3);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

}
