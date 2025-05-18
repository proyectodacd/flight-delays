package com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.sqlite;

import com.duodinamico.openweathermapfeeder.tools.mappers.WeatherResultMapper;
import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.domain.model.WeatherResult;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherStore;


import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class WeatherSQLStore implements WeatherStore {

    private final String databasePath;
    private SQLModifierWeather sqlModifierWeather;
    private SQLConnection sqlConnection;
    private WeatherResultMapper weatherResultMapper;

    public WeatherSQLStore(String databasePath, SQLModifierWeather sqlModifierWeather, SQLConnection sqlConnection, WeatherResultMapper weatherResultMapper) {
        this.databasePath = databasePath;
        this.sqlConnection = sqlConnection;
        this.weatherResultMapper = weatherResultMapper;
        this.sqlModifierWeather = sqlModifierWeather;
    }

    @Override
    public void saveWeather(WeatherResponse weatherResponse, String city) {
        try(Connection connection = this.sqlConnection.connect(this.databasePath)) {
            ArrayList<WeatherResult> weather = this.weatherResultMapper.getWeatherResult(weatherResponse, city);
            Statement statement = connection.createStatement();
            for(WeatherResult weatherResult : weather) {
                this.sqlModifierWeather.insertWeather(statement, weatherResult, city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
