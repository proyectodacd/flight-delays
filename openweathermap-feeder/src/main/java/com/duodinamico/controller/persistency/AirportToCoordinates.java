package com.duodinamico.controller.persistency;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.*;

public class AirportToCoordinates {

    private final HashMap<String, double[]> airports;

    public AirportToCoordinates(String[] args) {
        String filePath = args[0];
        Map<String, double[]> airportCoordinates = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            records.remove(0);

            for (String[] values : records) {
                if (values.length >= 7) {
                    String airportName = values[2].trim();
                    try {
                        double latitude = Double.parseDouble(values[5].trim());
                        double longitude = Double.parseDouble(values[6].trim());
                        airportCoordinates.put(airportName, new double[]{latitude, longitude});
                    } catch (NumberFormatException e) {
                        System.err.println("❌ Error al convertir coordenadas para: " + airportName);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        this.airports = (HashMap<String, double[]>) airportCoordinates;

    }

    public HashMap<String, double[]> getAirports() {
        return this.airports;
    }

    public double[] getAirportCoordinates(String airportIata) {
        return getAirports().get(airportIata);
    }


}
