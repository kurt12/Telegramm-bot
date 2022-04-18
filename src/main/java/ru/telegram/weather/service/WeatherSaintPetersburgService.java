package ru.telegram.weather.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface WeatherSaintPetersburgService extends WeatherService {
    SendMessage handle(final long chatId, Message message);
}
