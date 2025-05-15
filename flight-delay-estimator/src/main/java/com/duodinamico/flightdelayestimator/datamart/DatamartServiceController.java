package com.duodinamico.flightdelayestimator.datamart;
import com.duodinamico.flightdelayestimator.datamart.tools.DatamartManager;
import com.duodinamico.flightdelayestimator.datamart.history.MatchingFinderForHistoryEvents;
import com.duodinamico.flightdelayestimator.datamart.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.flightdelayestimator.datamart.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.flightdelayestimator.datamart.tools.TaskScheduler;
import com.duodinamico.flightdelayestimator.datamart.tools.ValuableContentMatcher;
import jakarta.jms.JMSException;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DatamartServiceController {

    private final ValuableContentMatcher valuableContentMatcher;
    private final MatchingFinderForHistoryEvents matchingFinderForHistoryEvents;
    private final DatamartManager datamartManager;
    private final EventListenerForRealTimeEvents eventListenerForRealTimeEvents;
    private final MatchingFinderForRealTimeEvents matchingFinderForRealTimeEvents;
    private final TaskScheduler taskScheduler;

    public DatamartServiceController(ValuableContentMatcher valuableContentMatcher, MatchingFinderForHistoryEvents matchingFinderForHistoryEvents, DatamartManager datamartManager, EventListenerForRealTimeEvents eventListenerForRealTimeEvents, MatchingFinderForRealTimeEvents matchingFinderForRealTimeEvents, TaskScheduler taskScheduler) {
        this.eventListenerForRealTimeEvents = eventListenerForRealTimeEvents;
        this.valuableContentMatcher = valuableContentMatcher;
        this.matchingFinderForHistoryEvents = matchingFinderForHistoryEvents;
        this.datamartManager = datamartManager;
        this.matchingFinderForRealTimeEvents = matchingFinderForRealTimeEvents;
        this.taskScheduler = taskScheduler;
    }

    public void execute() throws IOException, InterruptedException {
        //ºthis.datamartManager.deleteWholeDatamart();
        System.out.println("Bienvenido a FlightDelays®");
        System.out.println("----------------------------");
        //saveHistoryToDatamart();
        System.out.println("----------------------------");
        listenToRealTimeEvents();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable refreshDatamart = () -> {matchRealTimeEvents();};
        this.taskScheduler.programarTareaCadaMinuto(scheduler, refreshDatamart);
        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void saveHistoryToDatamart() {
        try {
            System.out.println("Cargando histórico de datos...");
            this.datamartManager.writeCleanContentToDatamart(this.valuableContentMatcher.mapToValuableContent(this.matchingFinderForHistoryEvents.findPossibleMatches()));
            System.out.println("Histórico de datos cargado con éxito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenToRealTimeEvents() {
        try {
            this.eventListenerForRealTimeEvents.consumeRealTimeEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void matchRealTimeEvents() {
        try {
            if (this.matchingFinderForRealTimeEvents.findPossibleMatchesForRealTimeEvents().size() != 0) {
                System.out.println("Actualizando Datamart a partir de eventos en tiempo real...");
                this.datamartManager.writeCleanContentToDatamart(this.valuableContentMatcher.mapToValuableContent(this.matchingFinderForRealTimeEvents.findPossibleMatchesForRealTimeEvents()));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}