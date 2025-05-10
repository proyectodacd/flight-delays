package com.duodinamico;

import com.duodinamico.controller.TaskScheduler;
import com.duodinamico.controller.WeatherController;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.eventintegration.WeatherEventStore;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.WeatherSQLStore;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController(new OpenWeatherMapProvider(args[3]), new WeatherEventStore(args[1]), new TaskScheduler(), new AirportToCoordinates(args[2]));
        controller.execute();

    }
}
