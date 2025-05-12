package es.ulpgc.com.duodinamico.tools;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.domain.model.FlightEvent;
import es.ulpgc.com.duodinamico.modelling.ValuableContentForPrediction;

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
