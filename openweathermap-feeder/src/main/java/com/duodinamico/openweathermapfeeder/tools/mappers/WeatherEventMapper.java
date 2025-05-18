package com.duodinamico.openweathermapfeeder.tools.mappers;

import com.duodinamico.openweathermapfeeder.domain.schema.WeatherInformation;
import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.openweathermapfeeder.tools.converters.UnixToUTCDateFormatter;

import java.util.ArrayList;

public class WeatherEventMapper {
    public ArrayList<WeatherEvent> getWeatherEvent(WeatherResponse weatherResponse, String city) {
        ArrayList<WeatherEvent> weatherEventArrayList = new ArrayList<>();
        UnixToUTCDateFormatter unixToUTCDateFormatter = new UnixToUTCDateFormatter();
        for (WeatherInformation weatherInformation : weatherResponse.getList()){
            WeatherEvent weatherEvent = new WeatherEvent(city,
                    weatherInformation.getDataCalculationTime(),
                    unixToUTCDateFormatter.formatUnixSecondsToISO8601UTC(weatherInformation.getDataCalculationTime()),
                    weatherInformation.getThermalConditions().getTemperature(),
                    weatherInformation.getWind().getSpeed(),
                    weatherInformation.getWind().getDeg(),
                    weatherInformation.getClouds().getCloudiness(),
                    weatherInformation.getRain().getRainOneHour(),
                    weatherInformation.getSnow().getSnowOneHour(),
                    weatherInformation.getDescription().getFirst().getDescription());
            weatherEventArrayList.add(weatherEvent);
        }
        return weatherEventArrayList;
    }
}
