package ru.telegram.weather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersDTO {

    private  Long telegramId;
    @NotNull
    private String firstName;
    private String lastName;
    private LocalDateTime dateTime;
    private String username;
    private Double longitude;
    private Double latitude;

}

//PostMaping ("save") -> @RequestBody UsersDTO
//PutMapping ("update/{id}) @RequestBody UsersDTO,Id;

