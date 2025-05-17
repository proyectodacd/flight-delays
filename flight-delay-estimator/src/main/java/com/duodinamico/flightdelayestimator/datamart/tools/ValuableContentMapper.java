package com.duodinamico.flightdelayestimator.datamart.tools;

import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.flightdelayestimator.datamart.modelling.ValuableContentForPrediction;

public class ValuableContentMapper {

    public ValuableContentForPrediction mapToDepartureValuableContent(FlightEvent flightEvent, WeatherEvent weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.getEstimatedDepartureTime(),flightEvent.getDepartureTimezone(),
                "Departure", flightEvent.getDepartureIata(), weatherEvent.getTemperature(), weatherEvent.getPercentageOfClouds(), weatherEvent.getWindSpeed(),
                weatherEvent.getWindDirection(), weatherEvent.getPrecipitation(), weatherEvent.getSnowmeasurement(), weatherEvent.getWeatherDescription(), flightEvent.getDepartureDelay());
    }

    public ValuableContentForPrediction mapToArrivalValuableContent(FlightEvent flightEvent, WeatherEvent weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.getEstimatedArrivalTime(), flightEvent.getArrivalTimezone(), "Arrival", flightEvent.getArrivalIata(), weatherEvent.getTemperature(), weatherEvent.getPercentageOfClouds(), weatherEvent.getWindSpeed(),
                weatherEvent.getWindDirection(), weatherEvent.getPrecipitation(), weatherEvent.getSnowmeasurement(), weatherEvent.getWeatherDescription(), flightEvent.getArrivalDelay());
    }
}
