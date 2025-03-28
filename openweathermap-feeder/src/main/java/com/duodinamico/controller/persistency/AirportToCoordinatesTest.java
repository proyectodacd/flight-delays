package com.duodinamico.controller.persistency;

import java.io.IOException;
import java.io.*;
import java.util.*;

public class AirportToCoordinatesTest {
    public static void main(String[] args) {
        String filePath = args[2];
        Map<String, double[]> airportCoordinates = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.replace("\"", "").split(",");

                if (values.length >= 7) {
                    String airportName = values[4].trim();
                    String latStr = values[5].trim().replaceAll("[^0-9.-]", "");
                    String lonStr = values[6].trim().replaceAll("[^0-9.-]", "");

                    if (!latStr.isEmpty() && !lonStr.isEmpty()) {
                        try {
                            double latitude = Double.parseDouble(latStr);
                            double longitude = Double.parseDouble(lonStr);
                            airportCoordinates.put(airportName, new double[]{latitude, longitude});
                        } catch (NumberFormatException e) {
                            System.err.println("❌ Error al convertir coordenadas para: " + airportName +
                                    " (lat: " + latStr + ", lon: " + lonStr + ")");
                        }
                    } else {
                        System.err.println("⚠️ Coordenadas vacías para: " + airportName);
                    }
                }
            }
        } catch (IOException e) {
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