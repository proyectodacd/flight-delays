package com.duodinamico.openweathermapfeeder;

import com.duodinamico.openweathermapfeeder.tools.scheduler.TaskScheduler;
import com.duodinamico.openweathermapfeeder.application.usecases.collectorandstore.WeatherController;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq.WeatherEventStore;
import com.duodinamico.openweathermapfeeder.tools.converters.AirportToCoordinates;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController(new OpenWeatherMapProvider(args[3], Arrays.copyOfRange(args,4,args.length)), new WeatherEventStore(args[1]), new TaskScheduler(), new AirportToCoordinates(args[2]));
        controller.execute();

    }
}
