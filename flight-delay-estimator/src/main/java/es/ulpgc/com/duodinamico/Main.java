package es.ulpgc.com.duodinamico;

public class Main {
    public static void main(String[] args) {
        DatamartServiceController datamartServiceController = new DatamartServiceController(new ValuableContentMatcher(), new MatchingFinderForHistoryEvents(new FlightEventHistoryLoader(args[0]), new WeatherEventHistoryLoader(args[1])), new HistoryManagerForDatamart(args[2]));
        datamartServiceController.execute();
    }
}
