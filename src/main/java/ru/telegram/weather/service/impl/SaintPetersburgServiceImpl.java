package ru.telegram.weather.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.weather.config.bot.BotState;
import ru.telegram.weather.feign.YandexApiFeignClient;
import ru.telegram.weather.model.Weather;
import ru.telegram.weather.model.forecasts.Fact;
import ru.telegram.weather.service.WeatherSaintPetersburgService;

@Service
@RequiredArgsConstructor
public class SaintPetersburgServiceImpl implements WeatherSaintPetersburgService {

    private final YandexApiFeignClient yandexApiFeignClient;
    @Value("${yandex.api.key}")
    private String yandexApiKey;


    @Override
    public SendMessage handle(long chatId, Message message) {
        Weather weather = yandexApiFeignClient.getWeather("59.9311","30.3609",true,yandexApiKey);
        String textWeather = getWeatherSaintPetersburgNowFromYandexApiMessage(weather.getFact(), weather);
        return new SendMessage(String.valueOf(message.getChatId()), textWeather);
    }

    @Override
    public SendMessage handle(Message message) {
        Weather weather = yandexApiFeignClient.getWeather("59.9311","30.3609",true,yandexApiKey);
        String textWeather = getWeatherSaintPetersburgNowFromYandexApiMessage(weather.getFact(), weather);
        return new SendMessage(String.valueOf(message.getChatId()), textWeather);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SAINT_PETERSBURG;
    }

    private String getWeatherSaintPetersburgNowFromYandexApiMessage(Fact fact, Weather weather) {
        StringBuilder weatherStringBuilder = new StringBuilder();
        weatherStringBuilder.append("Погода в CПБ сейчас:\n");
        weatherStringBuilder.append("Температура: ").append(fact.getTemp()).append("°C\n");
        weatherStringBuilder.append("Ощущаемая температура: ").append(fact.getFeelsLike()).append("°C\n");
        weatherStringBuilder.append("Погодное описание: ").append(weather.getFact().getCondition()).append("\n");
        weatherStringBuilder.append("Давление (в мм рт. ст.): ").append(fact.getPressureMm()).append("\n");
        weatherStringBuilder.append("Влажность воздуха: ").append(fact.getHumidity()).append("%\n");
        weatherStringBuilder.append("Восход Солнца: ").append(weather.getForecastsList().get(0).getSunrise()).append("\n");
        weatherStringBuilder.append("Закат Солнца: ").append(weather.getForecastsList().get(0).getSunset());
        return weatherStringBuilder.toString();
    }
    }

