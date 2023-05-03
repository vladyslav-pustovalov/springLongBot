package com.sprint_long_bot.service;

/**
 * @author Vladyslav Pustovalov
 * class which consist of Bot Keyboards that are shown to users
 */

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardSetter {

    /**
     * Method which initializes default keyboard for the bot
     */
    public ReplyKeyboardMarkup setDefaultKeyboard() {
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
