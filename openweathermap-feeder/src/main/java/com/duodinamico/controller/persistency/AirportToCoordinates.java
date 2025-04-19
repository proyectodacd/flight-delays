package com.duodinamico.controller.persistency;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.*;

public class AirportToCoordinates {

    private final HashMap<String, Coordinates> airports;
    private final String filePath;

    public AirportToCoordinates(String filePath) {
        this.filePath = filePath;
        Map<String, Coordinates> airportCoordinates = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            records.remove(0);

            for (String[] values : records) {
                if (values.length >= 7) {
                    String airportName = values[2].trim();
                    try {
                        double latitude = Double.parseDouble(values[5].trim());
                        double longitude = Double.parseDouble(values[6].trim());
                        airportCoordinates.put(airportName, new Coordinates(latitude, longitude));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir coordenadas para: " + airportName);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        this.airports = (HashMap<String, Coordinates>) airportCoordinates;

    }

    public HashMap<String, Coordinates> getAirports() {
        return this.airports;
    }

    public Coordinates getAirportCoordinates(String airportIata) {
        return this.airports.get(airportIata);
    }


}
