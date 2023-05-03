package com.sprint_long_bot.config;

/**
 * @author Vladyslav Pustovalov
 * class which contains Configuration bot data
 */

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotConfig {

    final  String botName = System.getenv("botName");
    final  String botToken = System.getenv("botToken");

    final DefaultBotOptions botOptions = new DefaultBotOptions();


    public String getBotName() {
        return botName;
    }

    @Bean
    public String getBotToken() {
        return botToken;
    }

    @Bean
    public DefaultBotOptions getBotOptions() {
        return botOptions;
    }
}
