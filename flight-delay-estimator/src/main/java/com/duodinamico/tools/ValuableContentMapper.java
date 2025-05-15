package com.duodinamico.tools;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.modelling.ValuableContentForPrediction;

public class ValuableContentMapper {

    public ValuableContentForPrediction departureContentMatcher(FlightEvent flightEvent, WeatherEvent weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.getEstimatedDepartureTime(),flightEvent.getDepartureTimezone(),
                "Departure", flightEvent.getDepartureIata(), weatherEvent.getTemperature(), weatherEvent.getWindSpeed(),
                weatherEvent.getWindDirection(), weatherEvent.getPrecipitation(), weatherEvent.getSnowmeasurement(), weatherEvent.getWeatherDescription(), flightEvent.getDepartureDelay());
    }

    public ValuableContentForPrediction     arrivalContentMatcher(FlightEvent flightEvent, WeatherEvent weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.getEstimatedArrivalTime(), flightEvent.getArrivalTimezone(), "Arrival", flightEvent.getArrivalIata(), weatherEvent.getTemperature(), weatherEvent.getWindSpeed(),
                weatherEvent.getWindDirection(), weatherEvent.getPrecipitation(), weatherEvent.getSnowmeasurement(), weatherEvent.getWeatherDescription(), flightEvent.getArrivalDelay());
    }
}
