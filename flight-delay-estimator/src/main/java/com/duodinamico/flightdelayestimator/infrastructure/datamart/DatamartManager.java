package com.duodinamico.flightdelayestimator.infrastructure.datamart;

import com.opencsv.CSVWriter;
import com.duodinamico.flightdelayestimator.domain.ValuableContentForPrediction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatamartManager {

    private String cleanDatamartPath;
    private String datamartPartitionForRawFlightDataPath;
    private String datamartPartitionForRawWeatherDataPath;
    public DatamartManager(String cleanDatamartPath, String datamartPartitionForRawFlightDataPath,  String datamartPartitionForRawWeatherDataPath) {
        this.cleanDatamartPath = cleanDatamartPath;
        this.datamartPartitionForRawFlightDataPath = datamartPartitionForRawFlightDataPath;
        this.datamartPartitionForRawWeatherDataPath = datamartPartitionForRawWeatherDataPath;
    }

    public void writeCleanContentToDatamart(ArrayList<ValuableContentForPrediction> valuableContentForPredictionArrayList) throws IOException {

        boolean fileExists = new File(this.cleanDatamartPath).exists();
        try (CSVWriter writer = new CSVWriter(new FileWriter(this.cleanDatamartPath, true))) {
            if (!fileExists) {
                writer.writeNext(new String[] {
                        "standardTime", "timezone", "airportType", "airportName", "temperature", "percentageOfClouds", "windSpeed", "windDirection", "precipitations", "snowMeasurement", "description", "delay"
                });
            }
            for (ValuableContentForPrediction valuableContent : valuableContentForPredictionArrayList) {
                writer.writeNext(new String[] {
                        valuableContent.getStandardTime(), valuableContent.getTimezone(), valuableContent.getAirportType(), valuableContent.getAirportName(), String.valueOf(valuableContent.getTemperature()), String.valueOf(valuableContent.getPercentageOfClouds()), String.valueOf(valuableContent.getWindSpeed()), String.valueOf(valuableContent.getWindDirection()), String.valueOf(valuableContent.getPrecipitations()), String.valueOf(valuableContent.getSnow()), valuableContent.getDescription(), String.valueOf(valuableContent.getDelay())
                });
            }
            deleteRawDatamartPartitions();
        }
    }

    public void deleteWholeDatamart() throws IOException {
        for (String path : List.of(this.cleanDatamartPath, this.datamartPartitionForRawFlightDataPath, this.datamartPartitionForRawWeatherDataPath)) {
            File file = new File(path);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.err.println("No se pudo borrar: " + path);
                }
            }
        }
    }

    public void deleteRawDatamartPartitions() throws IOException {
        for (String path : List.of(this.datamartPartitionForRawFlightDataPath, this.datamartPartitionForRawWeatherDataPath)) {
            File file = new File(path);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.err.println("No se pudo borrar: " + path);
                }
            }
        }
    }
}
