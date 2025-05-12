package es.ulpgc.com.duodinamico;

import es.ulpgc.com.duodinamico.history.HistoryManagerForDatamart;
import es.ulpgc.com.duodinamico.history.MatchingFinderForHistoryEvents;
import es.ulpgc.com.duodinamico.realtime.EventListenerForRealTimeEvents;
import es.ulpgc.com.duodinamico.tools.ValuableContentMatcher;
import jakarta.jms.JMSException;

import java.io.IOException;
import java.text.ParseException;

public class DatamartServiceController {

    private final ValuableContentMatcher valuableContentMatcher;
    private final MatchingFinderForHistoryEvents matchingFinderForHistoryEvents;
    private final HistoryManagerForDatamart historyManagerForDatamart;
    private final EventListenerForRealTimeEvents eventListenerForRealTimeEvents;

    public DatamartServiceController(ValuableContentMatcher valuableContentMatcher, MatchingFinderForHistoryEvents matchingFinderForHistoryEvents, HistoryManagerForDatamart historyManagerForDatamart, EventListenerForRealTimeEvents eventListenerForRealTimeEvents) {
        this.eventListenerForRealTimeEvents = eventListenerForRealTimeEvents;
        this.valuableContentMatcher = valuableContentMatcher;
        this.matchingFinderForHistoryEvents = matchingFinderForHistoryEvents;
        this.historyManagerForDatamart = historyManagerForDatamart;

    }

    public void execute(){
        System.out.println("Bienvenido a FlightDelays®");
        System.out.println("----------------------------");
        saveHistoryToDatamart();
        System.out.println("----------------------------");
        try {
            this.eventListenerForRealTimeEvents.consumeRealTimeEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        // siempre, 5 min despues de pedir climas, intenta matchear los csvs sucios

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
