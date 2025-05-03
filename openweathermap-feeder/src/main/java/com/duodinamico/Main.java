package com.duodinamico;

import com.duodinamico.controller.TaskScheduler;
import com.duodinamico.controller.WeatherController;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.eventintegration.WeatherEventSender;
import com.duodinamico.controller.persistency.WeatherSQLStore;
import com.duodinamico.domain.ports.FlightStore;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventStore;
import com.duodinamico.infrastructure.adapters.sqlite.FlightSQLStore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController(new OpenWeatherMapProvider(args[3]), new FlightEventStore(args[2]), new WeatherEventSender(args[1], args[3], args[2]), new TaskScheduler());
        controller.execute();

    }
}
