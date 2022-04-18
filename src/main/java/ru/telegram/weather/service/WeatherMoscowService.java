package ru.telegram.weather.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface WeatherMoscowService  extends WeatherService{
    SendMessage handleButton(Message message,long chatId );
}
