package com.duodinamico.controller.persistency;

import com.duodinamico.model.FlightModel;
import com.duodinamico.model.WeatherResult;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

public class SQLModifierWeather {

    public void insertDepartureWeather(Statement statement, FlightModel flight, WeatherResult weatherResult) throws SQLException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            UnixConverter converter = new UnixConverter();
            statement.execute("insert into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                    flight.getFlightIcao() + "', '" +
                    converter.convertToUnix(flight.getEstimatedDepartureTime(),flight.getDepartureTimezone()) + "'," +
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

    public void insertArrivalWeather(Statement statement, FlightModel flight, WeatherResult weatherResult) throws SQLException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            UnixConverter converter = new UnixConverter();
            statement.execute("insert into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                    flight.getFlightIcao() + "', '" +
                    converter.convertToUnix(flight.getEstimatedArrivalTime(),flight.getArrivalTimezone()) + "'," +
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

