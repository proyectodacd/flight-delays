package com.duodinamico.controller.persistency;

import com.duodinamico.model.Flight;
import com.duodinamico.model.WeatherResult;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SQLModifierWeather {

    public void insertDepartureWeather(Statement statement, Flight flight, WeatherResult weatherResult) throws SQLException {
        try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                UnixConverter converter = new UnixConverter();
                statement.execute("insert into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                        flight.getFlightId().getFlightIcao() + "', '" +
                        converter.convertToUnix(flight.getDeparture().getScheduled(),flight.getDeparture().getTimezone()) + "'," +
                        "'" + flight.getDeparture().getScheduled() + "'," +
                        "'" + flight.getDeparture().getAirport() + "'," +
                        "'" + flight.getDeparture().getTimezone() + "'," +
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

    public void insertArrivalWeather(Statement statement, Flight flight, WeatherResult weatherResult) throws SQLException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            UnixConverter converter = new UnixConverter();
            statement.execute("insert into weather (flight_icao, unixtime, standard_time, airport_name, timezone, temperature, feels_like, wind_speed, wind_direction, precipitations, snow_measurement, description, airport_description) values ('" +
                    flight.getFlightId().getFlightIcao() + "', '" +
                    converter.convertToUnix(flight.getArrival().getScheduled(),flight.getArrival().getTimezone()) + "'," +
                    "'" + flight.getArrival().getScheduled() + "'," +
                    "'" + flight.getArrival().getAirport() + "'," +
                    "'" + flight.getArrival().getTimezone() + "'," +
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

