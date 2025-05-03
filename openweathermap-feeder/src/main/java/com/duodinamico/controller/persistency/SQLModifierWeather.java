package com.duodinamico.controller.persistency;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.controller.model.WeatherResult;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class SQLModifierWeather {

    public void insertDepartureWeather(Statement statement, FlightEvent flight, WeatherResult weatherResult) throws SQLException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            UnixConverter converter = new UnixConverter();
            statement.execute("create table if not exists weather (" +
                    "    flight_icao text," +
                    "    unixtime integer," +
                    "    standard_time text," +
                    "    airport_name text," +
                    "    timezone text," +
                    "    temperature real," +
                    "    feels_like real," +
                    "    wind_speed real," +
                    "    wind_direction integer," +
                    "    precipitations real," +
                    "    snow_measurement real," +
                    "    description text," +
                    "    airport_description text," +
                    "    primary key (flight_icao, standard_time)" +
                    ");");
            statement.execute("insert or ignore into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                    flight.getFlightIcao() + "', '" +
                    converter.convertToUnix(flight.getEstimatedDepartureTime()) + "'," +
                    "'" + flight.getEstimatedDepartureTime() + "'," +
                    "'" + flight.getDepartureAirport() + "'," +
                    "'" + flight.getDepartureTimezone() + "'," +
                    "'" + weatherResult.getTemperature() + "'," +
                    "'" + weatherResult.getFeelsLike() + "'," +
                    "'" + weatherResult.getWindSpeed() + "'," +
                    "'" + weatherResult.getWindDirection() + "'," +
                    "'" + weatherResult.getPrecipitation() + "'," +
                    "'" + weatherResult.getSnowmeasurement() + "'," +
                    "'" + weatherResult.getWeatherDescription() + "'," +
                    "'" + "departure" + "')" +
                    ";");


        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void insertArrivalWeather(Statement statement, FlightEvent flight, WeatherResult weatherResult) throws SQLException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            UnixConverter converter = new UnixConverter();
            statement.execute("create table if not exists weather (" +
                    "    flight_icao text," +
                    "    unixtime integer," +
                    "    standard_time text," +
                    "    airport_name text," +
                    "    timezone text," +
                    "    temperature real," +
                    "    feels_like real," +
                    "    wind_speed real," +
                    "    wind_direction integer," +
                    "    precipitations real," +
                    "    snow_measurement real," +
                    "    description text," +
                    "    airport_description text," +
                    "    primary key (flight_icao, standard_time)" +
                    ");");
            statement.execute("insert or ignore into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                    flight.getFlightIcao() + "', '" +
                    converter.convertToUnix(flight.getEstimatedArrivalTime()) + "'," +
                    "'" + flight.getEstimatedArrivalTime() + "'," +
                    "'" + flight.getArrivalAirport() + "'," +
                    "'" + flight.getArrivalTimezone() + "'," +
                    "'" + weatherResult.getTemperature() + "'," +
                    "'" + weatherResult.getFeelsLike() + "'," +
                    "'" + weatherResult.getWindSpeed() + "'," +
                    "'" + weatherResult.getWindDirection() + "'," +
                    "'" + weatherResult.getPrecipitation() + "'," +
                    "'" + weatherResult.getSnowmeasurement() + "'," +
                    "'" + weatherResult.getWeatherDescription() + "'," +
                    "'" + "arrival" + "')" +
                    ";");


        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

