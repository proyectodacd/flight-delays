package com.duodinamico.flightdelayestimator.datamart.tools;

import com.opencsv.CSVWriter;
import com.duodinamico.flightdelayestimator.datamart.modelling.ValuableContentForPrediction;

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
        try (CSVWriter writer = new CSVWriter(new FileWriter(this.cleanDatamartPath))) {
            writer.writeNext(new String[] {"standardTime", "timezone", "airportType", "airportName", "temperature", "windSpeed", "windDirection", "precipitations", "snowMeasurement", "description", "delay"});
            for (ValuableContentForPrediction valuableContentForPrediction : valuableContentForPredictionArrayList) {
                writer.writeNext(new String[] {valuableContentForPrediction.getStandardTime(), valuableContentForPrediction.getTimezone(), valuableContentForPrediction.getAirportType(),
                        valuableContentForPrediction.getAirportName(), String.valueOf(valuableContentForPrediction.getTemperature()), String.valueOf(valuableContentForPrediction.getWindSpeed()),
                String.valueOf(valuableContentForPrediction.getWindDirection()), String.valueOf(valuableContentForPrediction.getPrecipitations()), String.valueOf(valuableContentForPrediction.getSnow()),
                        valuableContentForPrediction.getDescription(), String.valueOf(valuableContentForPrediction.getDelay())});
            }
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
}
