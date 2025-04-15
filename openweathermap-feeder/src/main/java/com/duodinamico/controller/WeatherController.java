package com.duodinamico.controller;

import com.duodinamico.controller.persistency.FlightSQLStore;
import com.duodinamico.controller.persistency.WeatherSQLStore;
import com.duodinamico.model.FlightModel;

import java.util.ArrayList;

public class WeatherController {
    private FlightSQLStore flightSQLStore;
    private WeatherSQLStore weatherStore;

    public WeatherController(String databasePath, String coordinatesDoc, String apiKey) {
        this.weatherStore = new WeatherSQLStore(databasePath, coordinatesDoc, apiKey);
        this.flightSQLStore = new FlightSQLStore(databasePath);
    }

    public void execute() {

        ArrayList<FlightModel> flightsList = flightSQLStore.loadFlights();
        for (FlightModel flight : flightsList) {
            try {
                weatherStore.saveWeather(flight);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Clima de aeropuertos guardados.");



    }
}
