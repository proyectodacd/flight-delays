package es.ulpgc.com.duodinamico;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryManagerForDatamart {

    private String filename;
    public HistoryManagerForDatamart(String datamartPath) {
        this.filename = datamartPath;
    }

    public void writeHistoryToDatamart(ArrayList<ValuableContentForPrediction> valuableContentForPredictionArrayList) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(this.filename))) {
            writer.writeNext(new String[] {"standardTime", "timezone", "airportType", "airportName", "temperature", "windSpeed", "windDirection", "precipitations", "snowMeasurement", "description", "delay"});
            for (ValuableContentForPrediction valuableContentForPrediction : valuableContentForPredictionArrayList) {
                writer.writeNext(new String[] {valuableContentForPrediction.getStandardTime(), valuableContentForPrediction.getTimezone(), valuableContentForPrediction.getAirportType(),
                        valuableContentForPrediction.getAirportName(), String.valueOf(valuableContentForPrediction.getTemperature()), String.valueOf(valuableContentForPrediction.getWindSpeed()),
                String.valueOf(valuableContentForPrediction.getWindDirection()), String.valueOf(valuableContentForPrediction.getPrecipitations()), String.valueOf(valuableContentForPrediction.getSnow()),
                        valuableContentForPrediction.getDescription(), String.valueOf(valuableContentForPrediction.getDelay())});
            }
        }

    }

    public void deleteDatamart(){

    }
}
