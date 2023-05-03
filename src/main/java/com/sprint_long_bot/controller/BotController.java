package com.sprint_long_bot.controller;

import com.sprint_long_bot.config.BotConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    public BotController() {
    }

    @GetMapping(path = "/hello")
    public String getHelloWorldFromBot() {
        return "Hello World from Bot!";
    }

    @PostMapping("/hello")
    public String takeUpdateFromRest(@RequestBody String update) {
        return "update is took"+update;
    }
}
