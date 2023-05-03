package com.sprint_long_bot.controller;

import com.sprint_long_bot.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BotController {
    private String update;

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public BotController() {
    }

    @GetMapping(path = "/hello")
    public String getHelloWorldFromBot() {
        log.info("GET works");
        return "Hello World from Bot!";
    }

    @PostMapping("/hello")
    public String takeUpdateFromRest(@RequestBody String update) {
        setUpdate(update);
        log.info("POST works, '"+update+"' is took via Postman");
        return "update is took"+update;
    }
}
