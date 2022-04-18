package ru.telegram.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.telegram.weather.model.forecasts.Fact;
import ru.telegram.weather.model.forecasts.Forecasts;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("now_dt")
    LocalDateTime nowDt;
    @JsonProperty("def_pressure_mm")
    Long defPressureMM;
    Fact fact;
    @JsonProperty("forecasts")
    List<Forecasts> forecastsList;
}
