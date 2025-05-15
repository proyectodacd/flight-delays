package com.duodinamico.flightdelayestimator;

import com.duodinamico.flightdelayestimator.datamart.DatamartServiceController;
import com.duodinamico.flightdelayestimator.datamart.history.FlightEventHistoryLoader;
import com.duodinamico.flightdelayestimator.datamart.tools.DatamartManager;
import com.duodinamico.flightdelayestimator.datamart.history.MatchingFinderForHistoryEvents;
import com.duodinamico.flightdelayestimator.datamart.history.WeatherEventHistoryLoader;
import com.duodinamico.flightdelayestimator.datamart.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.flightdelayestimator.datamart.realtime.processing.RealTimeFlightEventsLoader;
import com.duodinamico.flightdelayestimator.datamart.realtime.processing.RealTimeWeatherEventsLoader;
import com.duodinamico.flightdelayestimator.datamart.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.flightdelayestimator.datamart.realtime.storage.EventStorageForRealTimeEvents;
import com.duodinamico.flightdelayestimator.python.PythonInvoker;
import com.duodinamico.flightdelayestimator.datamart.tools.TaskScheduler;
import com.duodinamico.flightdelayestimator.datamart.tools.ValuableContentMatcher;
import com.duodinamico.flightdelayestimator.python.UserInterfaceController;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
//        DatamartServiceController datamartServiceController = new DatamartServiceController(new ValuableContentMatcher(), new MatchingFinderForHistoryEvents(new FlightEventHistoryLoader(args[0]), new WeatherEventHistoryLoader(args[1])), new DatamartManager(args[2],args[6],args[7]), new EventListenerForRealTimeEvents(args[3], List.of(Arrays.copyOfRange(args,4,6)), new EventStorageForRealTimeEvents(args[6],args[7])), new MatchingFinderForRealTimeEvents(new RealTimeFlightEventsLoader(args[6]),new RealTimeWeatherEventsLoader(args[7])), new TaskScheduler(), new PythonInvoker());
//        datamartServiceController.execute();

        UserInterfaceController userInterfaceController = new UserInterfaceController(new PythonInvoker(args[8]));
        userInterfaceController.execute();
    }
}
