package com.sprint_long_bot.config;

/**
 * @author Vladyslav Pustovalov
 * class which registers the created bot
 */

import com.sprint_long_bot.service.SpringLongBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotInitializer {

    final BotConfig config = new BotConfig();

    final SpringLongBot bot = new SpringLongBot(config.getBotOptions(), config.getBotToken());

    /**
     * Method which registers the created bot
     */
    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error("Error occurred: "+e.getMessage());
        }
    }
}
