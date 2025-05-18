package com.duodinamico.openweathermapfeeder;

import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProcessor;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.WeatherJSONParser;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq.WeatherEventSerializer;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.sqlite.SQLConnection;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.sqlite.SQLModifierWeather;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.sqlite.WeatherSQLStore;
import com.duodinamico.openweathermapfeeder.tools.converters.UnixUtils;
import com.duodinamico.openweathermapfeeder.tools.mappers.WeatherEventMapper;
import com.duodinamico.openweathermapfeeder.tools.mappers.WeatherResultMapper;
import com.duodinamico.openweathermapfeeder.tools.scheduler.TaskScheduler;
import com.duodinamico.openweathermapfeeder.application.usecases.collectorandstore.WeatherController;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq.WeatherEventStore;
import com.duodinamico.openweathermapfeeder.tools.converters.AirportToCoordinates;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        WeatherController controller = new WeatherController(new OpenWeatherMapProvider(new OpenWeatherMapProcessor(args[3]),new WeatherJSONParser(), Arrays.copyOfRange(args,4,args.length)), new WeatherEventStore(args[1],new WeatherEventMapper(),new WeatherEventSerializer()), new TaskScheduler(), new AirportToCoordinates(args[2]), new UnixUtils());

        controller.execute();
    }
}
