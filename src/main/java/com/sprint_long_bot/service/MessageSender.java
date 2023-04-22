package com.sprint_long_bot.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageSender {

    final KeyboardSetter keyboard = new KeyboardSetter();

    /**
     * Method which sends the welcome text to a user
     */
    public SendMessage startMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Welcome text");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends to a user the list with they subscription
     */
    public SendMessage userSubscriptionsList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("User's subscription list");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends the list with tender sites available for the subscription
     */
    public SendMessage availableSitesList(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Available sites for subscription");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends information and instructions for a user
     */
    public SendMessage helpMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Help text");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported commands
     */
    public SendMessage defaultMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Sorry, this command is not supported!\nPlease use the Keyboard buttons!");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }

    /**
     * Method which sends default answer on not supported update date format
     */
    public SendMessage warnMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("You are trying to make bad things!\nPlease use the Keyboard buttons!");
        message.setReplyMarkup(keyboard.setDefaultKeyboard());
        return message;
    }
}
