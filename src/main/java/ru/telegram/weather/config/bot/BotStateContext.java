package ru.telegram.weather.config.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.weather.service.WeatherMoscowService;
import ru.telegram.weather.service.WeatherSaintPetersburgService;
import ru.telegram.weather.service.WeatherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, WeatherService> messageHandlers = new HashMap<>();

    public BotStateContext(List<WeatherService> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        WeatherService weatherservice = findService(currentState);
        return weatherservice.handle(message);
    }
    public SendMessage processButton(BotState currentState, Message message, long chatId) {
        WeatherService currentService = findService(currentState);
        if(currentState.equals(BotState.MOSCOW)) {
            WeatherMoscowService weatherMoscowService = (WeatherMoscowService) currentService;
            return weatherMoscowService.handle(message);
        }
        else if(currentState.equals(BotState.SAINT_PETERSBURG)) {
            WeatherSaintPetersburgService weatherSaintPetersburgService = (WeatherSaintPetersburgService) currentService;
            return weatherSaintPetersburgService.handle(message);
        }
        else return null;
    }

    private WeatherService findService(BotState currentState) {
        if (isUsertTelegramState(currentState)) {
            return messageHandlers.get(BotState.MOSCOW);
        }

        return messageHandlers.get(currentState);
    }

    private boolean isUsertTelegramState(BotState currentState) {
        switch (currentState) {
            case MOSCOW:
                return true;
            default:
                return false;
        }
    }
}
