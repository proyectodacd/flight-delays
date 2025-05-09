package com.duodinamico.controller.persistency;

import com.duodinamico.controller.WeatherResultMapper;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.model.WeatherResult;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WeatherSQLStore implements WeatherStore{

    private final String databasePath;
    private SQLModifierWeather sqlModifierWeather;
    private SQLConnection sql;
    private WeatherResultMapper weatherResultMapper;

    public WeatherSQLStore(String databasePath, String coordinatesDocumentation) {
        this.databasePath = databasePath;
        this.sql = new SQLConnection();
        this.weatherResultMapper = new WeatherResultMapper();
        this.sqlModifierWeather = new SQLModifierWeather();
    }

    @Override
    public void saveWeather(WeatherResponse weatherResponse, String city) {
        try(Connection connection = this.sql.connect(this.databasePath)) {
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
