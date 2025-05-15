package com.duodinamico;

import com.duodinamico.history.FlightEventHistoryLoader;
import com.duodinamico.tools.DatamartManager;
import com.duodinamico.history.MatchingFinderForHistoryEvents;
import com.duodinamico.history.WeatherEventHistoryLoader;
import com.duodinamico.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.realtime.processing.RealTimeFlightEventsLoader;
import com.duodinamico.realtime.processing.RealTimeWeatherEventsLoader;
import com.duodinamico.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.realtime.storage.EventStorageForRealTimeEvents;
import com.duodinamico.tools.PythonInvoker;
import com.duodinamico.tools.TaskScheduler;
import com.duodinamico.tools.ValuableContentMatcher;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        DatamartServiceController datamartServiceController = new DatamartServiceController(new ValuableContentMatcher(), new MatchingFinderForHistoryEvents(new FlightEventHistoryLoader(args[0]), new WeatherEventHistoryLoader(args[1])), new DatamartManager(args[2],args[6],args[7]), new EventListenerForRealTimeEvents(args[3], List.of(Arrays.copyOfRange(args,4,6)), new EventStorageForRealTimeEvents(args[6],args[7])), new MatchingFinderForRealTimeEvents(new RealTimeFlightEventsLoader(args[6]),new RealTimeWeatherEventsLoader(args[7])), new TaskScheduler(), new PythonInvoker());
        datamartServiceController.execute();
    }
}
