package com.duodinamico.controller;


import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.persistency.SQLStore;

public class FlightController {
    private AviationStackProvider aviationStackProvider;
    private SQLStore sqliteStore;

    public FlightController() {
        this.aviationStackProvider = new AviationStackProvider();
        this.sqliteStore = new SQLStore();
    }

    public void execute(String[] args) {
        sqliteStore.saveFlights(aviationStackProvider.flightProvider(args), args);
    }
}
