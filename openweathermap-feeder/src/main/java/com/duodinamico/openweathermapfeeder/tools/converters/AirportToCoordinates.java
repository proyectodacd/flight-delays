package com.duodinamico.openweathermapfeeder.tools.converters;

import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.*;

public class AirportToCoordinates {

    private final HashMap<String, Coordinates> airports;
    private final String filePath;

    public AirportToCoordinates(String filePath) throws IOException, CsvException {
        this.filePath = filePath;

        try (CSVReader reader = new CSVReader(new FileReader(this.filePath))) {
            this.airports = (HashMap<String, Coordinates>) mapFileToCoordinatesMap(reader);
        }

    }

    public HashMap<String, Coordinates> getAirports() {
        return this.airports;
    }

    public Coordinates getAirportCoordinates(String airportIata) {
        return this.airports.get(airportIata);
    }

    public Map<String, Coordinates> mapFileToCoordinatesMap (CSVReader reader) throws CsvException, IOException {
        Map<String, Coordinates> airportCoordinates = new HashMap<>();
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
        return airportCoordinates;
    }
}
