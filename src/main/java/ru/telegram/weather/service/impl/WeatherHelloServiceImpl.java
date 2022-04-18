//package ru.telegram.weather.service.impl;
//
//import org.springframework.stereotype.Service;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import ru.telegram.weather.config.bot.BotState;
//import ru.telegram.weather.service.WeatherHelloService;
//
//@Service
//public class WeatherHelloServiceImpl implements WeatherHelloService {
//
//
//    @Override
//    public SendMessage handle(Message message) {
//         helloMessage = "Привет! Хочешь узнать погоду? Нажми на одну из этих кнопок:\n" +
//                "Москва;\nСанкт-Петербург.\nХочешь добавить свою погоду?\nПришли мне свою геопозицию.\n" +
//                "Для этого нажми на \"булавку\" и выбери раздел \"геопозиция\"";
//
//        return new SendMessage(String.valueOf(message.getChatId()),helloMessage);
//    }
//
//    @Override
//    public BotState getHandlerName() {
//        return BotState.START;
//    }
//}
