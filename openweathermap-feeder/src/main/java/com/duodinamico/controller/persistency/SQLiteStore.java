package com.duodinamico.controller.persistency;

import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.model.Flight;
import com.duodinamico.model.WeatherResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteStore implements WeatherStore{
    @Override
    public void saveWeather(Flight flight, String[] args){
        SQLConnection sql = new SQLConnection();
        SQLModifierWeather sqlModifierWeather = new SQLModifierWeather();
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider();
        UnixConverter unixConverter = new UnixConverter();
        AirportToCoordinates coordinates = new AirportToCoordinates(args);
        WeatherResult departureInfo = (coordinates.getAirportCoordinates(flight.getDeparture().getIata()) != null) ? openWeatherMapProvider.weatherProvider(String.valueOf(coordinates.getAirportCoordinates(flight.getDeparture().getIata())[0]),
                String.valueOf(coordinates.getAirportCoordinates(flight.getDeparture().getIata())[1]), String.valueOf(unixConverter.convertToUnix(flight.getDeparture().getScheduled(),flight.getDeparture().getTimezone())), args) : null;
        WeatherResult arrivalInfo = (coordinates.getAirportCoordinates(flight.getArrival().getIata()) != null) ? openWeatherMapProvider.weatherProvider(String.valueOf(coordinates.getAirportCoordinates(flight.getArrival().getIata())[0]),
                String.valueOf(coordinates.getAirportCoordinates(flight.getArrival().getIata())[1]), String.valueOf(unixConverter.convertToUnix(flight.getArrival().getScheduled(),flight.getArrival().getTimezone())), args) : null;
        try(Connection connection = sql.connect(args[1])) {
            Statement statement = connection.createStatement();
            sqlModifierWeather.insertDepartureWeather(statement,flight,departureInfo);
            sqlModifierWeather.insertArrivalWeather(statement,flight,arrivalInfo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
