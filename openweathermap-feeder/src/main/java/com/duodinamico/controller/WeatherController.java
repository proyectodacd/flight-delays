package com.duodinamico.controller;

import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.persistency.SQLStore;
import com.duodinamico.controller.persistency.SQLiteStore;
import com.duodinamico.model.Flight;

import java.util.List;

public class WeatherController {
    private SQLStore store;
    private SQLiteStore sqLiteStore;

    public WeatherController() {
        this.store = new SQLStore();
        this.sqLiteStore = new SQLiteStore();
    }

    public void execute(String[] args) {

        List<Flight> flightsList = store.loadFlights(args); // sacar lista de vuelos de ayer
        for (Flight flight : flightsList) {
            sqLiteStore.saveWeather(flight,args);
        }









    }
}
