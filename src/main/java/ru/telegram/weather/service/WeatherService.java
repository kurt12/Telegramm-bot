package ru.telegram.weather.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.weather.config.bot.BotState;

public interface WeatherService {
    SendMessage handle(Message message);

    BotState getHandlerName();
}
