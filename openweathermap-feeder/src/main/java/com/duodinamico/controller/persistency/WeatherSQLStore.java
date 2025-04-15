package com.duodinamico.controller.persistency;

import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.model.FlightModel;
import com.duodinamico.controller.model.WeatherResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WeatherSQLStore implements WeatherStore{

    private final String databasePath;
    private final String coordinatesDocumentation;
    private final String apiKey;

    public WeatherSQLStore(String databasePath, String coordinatesDocumentation, String apiKey) {
        this.databasePath = databasePath;
        this.coordinatesDocumentation = coordinatesDocumentation;
        this.apiKey = apiKey;
    }

    @Override
    public void saveWeather(FlightModel flight){
        SQLConnection sql = new SQLConnection();
        SQLModifierWeather sqlModifierWeather = new SQLModifierWeather();
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider(apiKey);
        UnixConverter unixConverter = new UnixConverter();
        AirportToCoordinates coordinates = new AirportToCoordinates(coordinatesDocumentation);
        WeatherResult departureInfo = (coordinates.getAirportCoordinates(flight.getDepartureIata()) != null) ? openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getDepartureIata()),String.valueOf(unixConverter.convertToUnix(flight.getEstimatedDepartureTime(),flight.getDepartureTimezone()))) : null;
        WeatherResult arrivalInfo = (coordinates.getAirportCoordinates(flight.getArrivalIata()) != null) ? openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getArrivalIata()),String.valueOf(unixConverter.convertToUnix(flight.getEstimatedArrivalTime(),flight.getArrivalTimezone()))) : null;

        try(Connection connection = sql.connect(databasePath)) {
            Statement statement = connection.createStatement();
            sqlModifierWeather.insertDepartureWeather(statement,flight,departureInfo);
            sqlModifierWeather.insertArrivalWeather(statement,flight,arrivalInfo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
