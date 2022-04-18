package ru.telegram.weather.model.parts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.telegram.weather.model.parts.timesofday.Day;
import ru.telegram.weather.model.parts.timesofday.Evening;
import ru.telegram.weather.model.parts.timesofday.Morning;
import ru.telegram.weather.model.parts.timesofday.Night;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parts {
    @JsonProperty("night")
    private Night night;
    @JsonProperty("morning")
    private Morning morning;
    @JsonProperty("day")
    private Day day;
    @JsonProperty("evening")
    private Evening evening;
}
