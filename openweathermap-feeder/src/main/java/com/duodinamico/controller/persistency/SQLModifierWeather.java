package com.duodinamico.controller.persistency;
import com.duodinamico.controller.model.WeatherResult;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class SQLModifierWeather {

    public void insertWeather(Statement statement, WeatherResult weatherResult, String city) throws SQLException {
        try {
            UnixToUTCDateFormatter utcFormatter = new UnixToUTCDateFormatter();
            statement.execute("create table if not exists weather (" +
                    " city text, " +
                    "    datacalculationtime integer," +
                    "    standard_time text," +
                    "    temperature real," +
                    "    feels_like real," +
                    "    wind_speed real," +
                    "    wind_direction integer," +
                    "    precipitations real," +
                    "    snow_measurement real," +
                    "    description text," +
                    "    primary key (city, dataCalculationTime)" +
                    ");");
            statement.execute("insert or ignore into weather (city, datacalculationtime, standard_time, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description) values ('" +
                     city + "', '" +
                    weatherResult.getDataCalculationTime() + "'," +
                    "'" + utcFormatter.formatUnixSecondsToISO8601UTC(weatherResult.getDataCalculationTime()) + "'," +
                    "'" + weatherResult.getTemperature() + "'," +
                    "'" + weatherResult.getFeelsLike() + "'," +
                    "'" + weatherResult.getWindSpeed() + "'," +
                    "'" + weatherResult.getWindDirection() + "'," +
                    "'" + weatherResult.getPrecipitation() + "'," +
                    "'" + weatherResult.getSnowmeasurement() + "'," +
                    "'" + weatherResult.getWeatherDescription() + "')" +
                    ";");
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

