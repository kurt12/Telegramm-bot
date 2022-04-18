package ru.telegram.weather.config.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.weather.mapper.UserMapper;
import ru.telegram.weather.service.UserDbService;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramFacade {

    private final BotStateContext botStateContext;
    private final UserMapper userMapper;
    private final UserDbService userDbService;

    public Optional<BotApiMethod<?>> handleUpdate(Update update) {
        Message message = update.getMessage();
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

            log.info("New callbackQuery from User: {}, userId: {}, with data: {}",
                    update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());

            // return processCallbackQuery(callbackQuery);
        } else if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());

            Optional<SendMessage> optionalSendMessage = handleInputMessage(message);
            if (optionalSendMessage.isPresent()) {
                return Optional.of(optionalSendMessage.get());
            }
        }
        return Optional.empty();

    }

    private Optional<SendMessage> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        log.info("Сохранение пользывателя");
        userDbService.save(userMapper.toUsersDTO(message));
        log.info("Пользыватель Сохранен");


        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.START;
                break;
            case "/Moscow":
            case "/moscow":
                botState = BotState.MOSCOW;
                break;
            case "/Saint_Petersburg":
            case "/saint_petersburg":
                botState = BotState.SAINT_PETERSBURG;
                break;
            case "/Rostov-on-don":
            case "/rostov_on_don":
                botState = BotState.ROSTOV_ON_DON;
                break;
            default:
                return Optional.empty();

        }

        replyMessage = botStateContext.processInputMessage(botState, message);

        return Optional.of(replyMessage);
    }

    private Optional<BotApiMethod<?>> processCallbackQuery(CallbackQuery buttonQuery) {
        final long chatId = buttonQuery.getMessage().getChatId();
        Message message = buttonQuery.getMessage();
        message.getFrom().setId(message.getChatId());
        message.getFrom().setFirstName(message.getChat().getFirstName());
        message.getFrom().setLastName(message.getChat().getLastName());
        message.getFrom().setUserName(message.getChat().getUserName());
        SendMessage replyMessage;
        switch (buttonQuery.getData()) {
            case "buttonMoscow":
                 //userDataCache.getUsersCurrentBotState(userId);
                replyMessage = botStateContext.processButton(BotState.MOSCOW, message, chatId);
                return Optional.of(replyMessage);
            case "buttonPetersburg":
                replyMessage = botStateContext.processButton(BotState.SAINT_PETERSBURG, message, chatId);
                return Optional.of(replyMessage);
        }

        return Optional.empty();
    }
}
