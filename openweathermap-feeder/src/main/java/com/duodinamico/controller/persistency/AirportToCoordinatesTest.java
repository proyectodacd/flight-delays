package com.duodinamico.controller.persistency;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.*;
import java.util.*;

public class AirportToCoordinatesTest {
    public static void main(String[] args) {
        String filePath = args[2];
        Map<String, double[]> airportCoordinates = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            records.remove(0); // Saltar la primera fila (encabezado)

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

        // Verificar cuántas entradas se cargaron
        System.out.println("Total aeropuertos cargados: " + airportCoordinates.size());

        // Imprimir algunos ejemplos
        airportCoordinates.forEach((key, value) ->
                System.out.println(key + " -> Lat: " + value[0] + ", Lng: " + value[1])
        );
    }
}