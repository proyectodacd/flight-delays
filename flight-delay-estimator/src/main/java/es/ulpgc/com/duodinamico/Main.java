package es.ulpgc.com.duodinamico;

import es.ulpgc.com.duodinamico.history.FlightEventHistoryLoader;
import es.ulpgc.com.duodinamico.history.HistoryManagerForDatamart;
import es.ulpgc.com.duodinamico.history.MatchingFinderForHistoryEvents;
import es.ulpgc.com.duodinamico.history.WeatherEventHistoryLoader;
import es.ulpgc.com.duodinamico.realtime.EventListenerForRealTimeEvents;
import es.ulpgc.com.duodinamico.realtime.EventStorageForRealTimeEvents;
import es.ulpgc.com.duodinamico.tools.ValuableContentMatcher;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatamartServiceController datamartServiceController = new DatamartServiceController(new ValuableContentMatcher(), new MatchingFinderForHistoryEvents(new FlightEventHistoryLoader(args[0]), new WeatherEventHistoryLoader(args[1])), new HistoryManagerForDatamart(args[2]), new EventListenerForRealTimeEvents(args[3], List.of(Arrays.copyOfRange(args,4,6)), new EventStorageForRealTimeEvents(args[6],args[7])));
        datamartServiceController.execute();
    }
}
