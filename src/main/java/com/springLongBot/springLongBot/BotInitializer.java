package com.springLongBot.springLongBot;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class BotInitializer {

    private final String botToken = System.getenv("botToken");

    @Bean
    private String getToken() {
        return botToken;
    }

    @Bean
    DefaultBotOptions options() {
        return new DefaultBotOptions();
    }

     SpringLongBot bot = new SpringLongBot(options(), getToken());

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
