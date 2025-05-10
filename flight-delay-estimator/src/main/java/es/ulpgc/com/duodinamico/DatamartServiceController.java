package es.ulpgc.com.duodinamico;

import java.io.IOException;
import java.text.ParseException;

public class DatamartServiceController {

    private final ValuableContentMatcher valuableContentMatcher;
    private final MatchingFinderForHistoryEvents matchingFinderForHistoryEvents;
    private final HistoryManagerForDatamart historyManagerForDatamart;

    public DatamartServiceController(ValuableContentMatcher valuableContentMatcher, MatchingFinderForHistoryEvents matchingFinderForHistoryEvents, HistoryManagerForDatamart historyManagerForDatamart) {
        this.valuableContentMatcher = valuableContentMatcher;
        this.matchingFinderForHistoryEvents = matchingFinderForHistoryEvents;
        this.historyManagerForDatamart = historyManagerForDatamart;
    }

    public void execute(){
        System.out.println("Bienvenido a FlightDelays.");
        System.out.println("duodinamico©");
        System.out.println("----------------------------");
        saveHistoryToDatamart();
        System.out.println("----------------------------");



    }

    public void saveHistoryToDatamart() {
        try {
            System.out.println("Cargando histórico de datos...");
            this.historyManagerForDatamart.writeHistoryToDatamart(this.valuableContentMatcher.mapToValuableContent(this.matchingFinderForHistoryEvents.findPossibleMatches()));
            System.out.println("Histórico de datos cargado con éxito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // TO DO borrar datamart
}
