package com.duodinamico.flightdelayestimator.tools;

import com.duodinamico.flightdelayestimator.domain.ValuableContentForPrediction;
import com.google.gson.JsonObject;

import java.util.*;

public class ValuableContentMatcher {

    private UnixConverter unixConverter = new UnixConverter();
    private ValuableContentMapper valuableContentMapper = new ValuableContentMapper();

    public ArrayList<ValuableContentForPrediction> matchCompatibleCouples(List<Map<List<JsonObject>, List<JsonObject>>> history) {
        ArrayList<ValuableContentForPrediction> valuableContentForPredictions = new ArrayList<ValuableContentForPrediction>();
        for (Map<List<JsonObject>, List<JsonObject>> couple : history) {
            for (List<JsonObject> flightEvents : couple.keySet()) {
                for (JsonObject flightEvent : flightEvents) {
                    if (mapToIndividualValuableContentFromDeparture(flightEvent, couple.get(flightEvents)) != null) {valuableContentForPredictions.add(mapToIndividualValuableContentFromDeparture(flightEvent, couple.get(flightEvents)));}
                    if (mapToIndividualValuableContentFromArrival(flightEvent, couple.get(flightEvents)) != null) {valuableContentForPredictions.add(mapToIndividualValuableContentFromArrival(flightEvent, couple.get(flightEvents)));}
                }
            }
        }
        return valuableContentForPredictions;
    }


    public ValuableContentForPrediction mapToIndividualValuableContentFromDeparture (JsonObject flightEvent, List<JsonObject> weatherEvents) {
        Map<Integer, Integer> timeDifferences = new HashMap<Integer, Integer>();
        boolean validator = false;
        for (JsonObject weatherEvent : weatherEvents) {
            if (flightEvent.get("departureIata").getAsString().equals(weatherEvent.get("city").getAsString()) && Math.abs(unixConverter.convertToUnix(flightEvent.get("estimatedDepartureTime").getAsString())-weatherEvent.get("dataCalculationTime").getAsInt()) < 1860){
                validator = true;
                timeDifferences.put(Math.abs(unixConverter.convertToUnix(flightEvent.get("estimatedDepartureTime").getAsString())-weatherEvent.get("dataCalculationTime").getAsInt()),weatherEvent.get("dataCalculationTime").getAsInt());
            }
        }
        Optional<JsonObject> resultado = validator == true ? weatherEvents.stream().filter(p -> p.get("city").getAsString().equals(flightEvent.get("departureIata").getAsString()) && p.get("dataCalculationTime").getAsInt() == (timeDifferences.get(Collections.min(timeDifferences.keySet())))).findFirst() : Optional.empty();
        if (resultado.isPresent()) {
            return valuableContentMapper.mapToDepartureValuableContent(flightEvent, resultado.get());
        }
        return null;
    }

    public ValuableContentForPrediction mapToIndividualValuableContentFromArrival (JsonObject flightEvent, List<JsonObject> weatherEvents) {
        Map<Integer, Integer> timeDifferences = new HashMap<Integer, Integer>();
        boolean validator = false;
        for (JsonObject weatherEvent : weatherEvents) {
            if (flightEvent.get("arrivalIata").getAsString().equals(weatherEvent.get("city").getAsString()) && Math.abs(unixConverter.convertToUnix(flightEvent.get("estimatedArrivalTime").getAsString())-weatherEvent.get("dataCalculationTime").getAsInt()) < 1860){
                validator = true;
                timeDifferences.put(Math.abs(unixConverter.convertToUnix(flightEvent.get("estimatedArrivalTime").getAsString())-weatherEvent.get("dataCalculationTime").getAsInt()),weatherEvent.get("dataCalculationTime").getAsInt());
            }
        }
        Optional<JsonObject> resultado = validator == true ? weatherEvents.stream().filter(p -> p.get("city").getAsString().equals(flightEvent.get("arrivalIata").getAsString()) && p.get("dataCalculationTime").getAsInt() == (timeDifferences.get(Collections.min(timeDifferences.keySet())))).findFirst() : Optional.empty();
        if (resultado.isPresent()) {
            return valuableContentMapper.mapToArrivalValuableContent(flightEvent, resultado.get());
        }
        return null;
    }
}
