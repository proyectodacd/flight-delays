package com.duodinamico.controller.persistency;

import com.duodinamico.controller.WeatherResultMapper;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.domain.model.FlightModel;
import com.duodinamico.controller.model.WeatherResult;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;
import com.duodinamico.infrastructure.adapters.mappers.FlightModelMapper;
import com.duodinamico.infrastructure.adapters.sqlite.SQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WeatherSQLStore implements WeatherStore{

    private final String databasePath;
    private SQLModifierWeather sqlModifierWeather;
    private SQLConnection sql;
    private OpenWeatherMapProvider openWeatherMapProvider;
    private UnixConverter unixConverter;
    private AirportToCoordinates coordinates;
    private WeatherResultMapper weatherResultMapper;



    public WeatherSQLStore(String databasePath, String coordinatesDocumentation, String apiKey) {
        this.databasePath = databasePath;
        this.sql = new SQLConnection();
        this.openWeatherMapProvider = new OpenWeatherMapProvider(apiKey);
        this.unixConverter = new UnixConverter();
        this.coordinates = new AirportToCoordinates(coordinatesDocumentation);
        this.weatherResultMapper = new WeatherResultMapper();
        this.sqlModifierWeather = new SQLModifierWeather();
    }

    @Override
    public void saveDepartureWeather(ArrayList<FlightModel> flights){

        try(Connection connection = sql.connect(databasePath)) {
            for(FlightModel flight : flights) {
                WeatherResponse departureInfo = (coordinates.getAirportCoordinates(flight.getDepartureIata()) != null) ? openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getDepartureIata()), String.valueOf(unixConverter.convertToUnix(flight.getEstimatedDepartureTime(), flight.getDepartureTimezone()))) : null;
                Statement statement = connection.createStatement();
                WeatherResult departure = weatherResultMapper.getWeatherResult(departureInfo);
                sqlModifierWeather.insertDepartureWeather(statement, flight, departure);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveArrivalWeather(ArrayList<FlightModel> flights){

        try(Connection connection = sql.connect(databasePath)) {
            for (FlightModel flight : flights){
                WeatherResponse arrivalInfo = (coordinates.getAirportCoordinates(flight.getArrivalIata()) != null) ? openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getArrivalIata()), String.valueOf(unixConverter.convertToUnix(flight.getEstimatedArrivalTime(), flight.getArrivalTimezone()))) : null;
                Statement statement = connection.createStatement();
                WeatherResult arrival = weatherResultMapper.getWeatherResult(arrivalInfo);
                sqlModifierWeather.insertArrivalWeather(statement, flight, arrival);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
