package ru.telegram.weather.mapper;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.weather.model.dto.UsersDTO;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UsersDTO toUsersDTO(Message message) {
        return UsersDTO.builder()
                .telegramId(message.getFrom().getId())
                .firstName(message.getFrom().getFirstName())
                .dateTime(LocalDateTime.now())
                .lastName(message.getFrom().getLastName())
                .username(message.getFrom().getUserName())
                .longitude(message.getLocation().getLongitude())
                .latitude(message.getLocation().getLatitude())
                .build();
    }


}