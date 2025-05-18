package com.duodinamico.flightdelayestimator;

import com.duodinamico.flightdelayestimator.application.usecases.managedatamart.DatamartServiceController;
import com.duodinamico.flightdelayestimator.application.usecases.userinterface.UserInterfaceController;
import com.duodinamico.flightdelayestimator.infrastructure.adapters.PythonInvoker;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.DatamartManager;
import com.duodinamico.flightdelayestimator.tools.*;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.history.FlightEventHistoryLoader;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.history.MatchingFinderForHistoryEvents;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.history.WeatherEventHistoryLoader;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.processing.RealTimeFlightEventsLoader;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.processing.RealTimeWeatherEventsLoader;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.storage.EventStorageForRealTimeEvents;
import com.duodinamico.flightdelayestimator.tools.ui.ProcessRepeater;
import com.duodinamico.flightdelayestimator.tools.ui.QueryTools;
import com.duodinamico.flightdelayestimator.tools.ui.UserInterfaceForQueries;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        DatamartServiceController datamartServiceController = new DatamartServiceController(new ValuableContentMatcher(), new MatchingFinderForHistoryEvents(new FlightEventHistoryLoader(args[0]), new WeatherEventHistoryLoader(args[1])), new DatamartManager(args[2],args[6],args[7]), new EventListenerForRealTimeEvents(args[3], List.of(Arrays.copyOfRange(args,4,6)), new EventStorageForRealTimeEvents(args[6],args[7])), new MatchingFinderForRealTimeEvents(new RealTimeFlightEventsLoader(args[6]),new RealTimeWeatherEventsLoader(args[7])), new TaskScheduler());
        UserInterfaceController userInterfaceController = new UserInterfaceController(new PythonInvoker(args[8],args[2]), new TaskScheduler(),new UserInterfaceForQueries(args[8],new QueryTools(), new ProcessRepeater()));

        datamartServiceController.execute();
        userInterfaceController.execute();
    }
}
