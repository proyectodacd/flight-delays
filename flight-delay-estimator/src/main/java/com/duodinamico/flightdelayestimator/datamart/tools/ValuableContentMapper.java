package com.duodinamico.flightdelayestimator.datamart.tools;

import com.duodinamico.flightdelayestimator.datamart.modelling.ValuableContentForPrediction;
import com.google.gson.JsonObject;

public class ValuableContentMapper {

    public ValuableContentForPrediction mapToDepartureValuableContent(JsonObject flightEvent, JsonObject weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.get("estimatedDepartureTime").getAsString(),flightEvent.get("departureTimezone").getAsString(),
                "Departure", flightEvent.get("departureIata").getAsString(), weatherEvent.get("temperature").getAsFloat(), weatherEvent.get("percentageOfClouds").getAsInt(), weatherEvent.get("windSpeed").getAsFloat(),
                weatherEvent.get("windDirection").getAsInt(), weatherEvent.get("precipitation").getAsFloat(), weatherEvent.get("snowmeasurement").getAsFloat(), weatherEvent.get("weatherDescription").getAsString(), flightEvent.get("departureDelay").getAsInt());
    }

    public ValuableContentForPrediction mapToArrivalValuableContent(JsonObject flightEvent, JsonObject weatherEvent) {
        return new ValuableContentForPrediction(flightEvent.get("estimatedArrivalTime").getAsString(), flightEvent.get("arrivalTimezone").getAsString(), "Arrival", flightEvent.get("arrivalIata").getAsString(), weatherEvent.get("temperature").getAsFloat(), weatherEvent.get("percentageOfClouds").getAsInt(), weatherEvent.get("windSpeed").getAsFloat(),
                weatherEvent.get("windDirection").getAsInt(), weatherEvent.get("precipitation").getAsFloat(), weatherEvent.get("snowmeasurement").getAsFloat(), weatherEvent.get("weatherDescription").getAsString(), flightEvent.get("arrivalDelay").getAsInt());
    }
}
