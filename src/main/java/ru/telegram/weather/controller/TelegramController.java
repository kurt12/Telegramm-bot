package ru.telegram.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.weather.config.bot.TelegramBot;
import ru.telegram.weather.feign.UserDbFeignClient;
import ru.telegram.weather.model.dto.UsersDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TelegramController {
    private final TelegramBot telegramBot;
    private final UserDbFeignClient userDbFeignClient;

    @PostMapping("/")
    public BotApiMethod<?> getWeather(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> findAll() {
        return ResponseEntity.ok(userDbFeignClient.findAll());
    }

    @GetMapping("/find-by-id{id}")
    public ResponseEntity<UsersDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userDbFeignClient.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UsersDTO usersDTO) {
        userDbFeignClient.save(usersDTO);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/update{id}")
    public ResponseEntity<?> update(@RequestBody UsersDTO user, @PathVariable Long id) {
        userDbFeignClient.update(user, id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userDbFeignClient.deleteById(id);
        return ResponseEntity.ok(null);

    }
}
