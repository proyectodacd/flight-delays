package es.ulpgc.com.duodinamico.tools;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.domain.model.FlightEvent;
import es.ulpgc.com.duodinamico.modelling.ValuableContentForPrediction;

import java.util.*;

public class ValuableContentMatcher {

    private UnixConverter unixConverter = new UnixConverter();
    private ValuableContentMapper valuableContentMapper = new ValuableContentMapper();

    public ArrayList<ValuableContentForPrediction> mapToValuableContent (List<Map<List<FlightEvent>, List<WeatherEvent>>> history) {
        ArrayList<ValuableContentForPrediction> valuableContentForPredictions = new ArrayList<ValuableContentForPrediction>();
        for (Map<List<FlightEvent>, List<WeatherEvent>> couple : history) {
            for (List<FlightEvent> flightEvents : couple.keySet()) {
                for (FlightEvent flightEvent : flightEvents) {
                    if (mapToIndividualValuableContentFromDeparture(flightEvent, couple.get(flightEvents)) != null) {valuableContentForPredictions.add(mapToIndividualValuableContentFromDeparture(flightEvent, couple.get(flightEvents)));}
                    if (mapToIndividualValuableContentFromArrival(flightEvent, couple.get(flightEvents)) != null) {valuableContentForPredictions.add(mapToIndividualValuableContentFromArrival(flightEvent, couple.get(flightEvents)));}
                }
            }
        }
        return valuableContentForPredictions;
    }


    public ValuableContentForPrediction mapToIndividualValuableContentFromDeparture (FlightEvent flightEvent, List<WeatherEvent> weatherEvents) {
        Map<Integer, Integer> timeDifferences = new HashMap<Integer, Integer>();
        boolean validator = false;
        for (WeatherEvent weatherEvent : weatherEvents) {
            if (flightEvent.getDepartureIata().equals(weatherEvent.getCity()) && Math.abs(unixConverter.convertToUnix(flightEvent.getEstimatedDepartureTime())-weatherEvent.getDataCalculationTime()) < 1860){
                validator = true;
                timeDifferences.put(Math.abs(unixConverter.convertToUnix(flightEvent.getEstimatedDepartureTime())-weatherEvent.getDataCalculationTime()),weatherEvent.getDataCalculationTime());
            }
        }
        Optional<WeatherEvent> resultado = validator == true ? weatherEvents.stream().filter(p -> p.getCity().equals(flightEvent.getDepartureIata()) && p.getDataCalculationTime() == (timeDifferences.get(Collections.min(timeDifferences.keySet())))).findFirst() : Optional.empty();
        if (resultado.isPresent()) {
            return valuableContentMapper.departureContentMatcher(flightEvent, resultado.get());
        }
        return null;
    }

    public ValuableContentForPrediction mapToIndividualValuableContentFromArrival (FlightEvent flightEvent, List<WeatherEvent> weatherEvents) {
        Map<Integer, Integer> timeDifferences = new HashMap<Integer, Integer>();
        boolean validator = false;
        for (WeatherEvent weatherEvent : weatherEvents) {
            if (flightEvent.getArrivalIata().equals(weatherEvent.getCity()) && Math.abs(unixConverter.convertToUnix(flightEvent.getEstimatedArrivalTime())-weatherEvent.getDataCalculationTime()) < 1860){
                validator = true;
                timeDifferences.put(Math.abs(unixConverter.convertToUnix(flightEvent.getEstimatedArrivalTime())-weatherEvent.getDataCalculationTime()),weatherEvent.getDataCalculationTime());
            }
        }
        Optional<WeatherEvent> resultado = validator == true ? weatherEvents.stream().filter(p -> p.getCity().equals(flightEvent.getArrivalIata()) && p.getDataCalculationTime() == (timeDifferences.get(Collections.min(timeDifferences.keySet())))).findFirst() : Optional.empty();
        if (resultado.isPresent()) {
            return valuableContentMapper.arrivalContentMatcher(flightEvent, resultado.get());
        }
        return null;
    }
}
