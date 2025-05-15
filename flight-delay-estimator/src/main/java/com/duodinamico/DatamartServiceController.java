package com.duodinamico;
import com.duodinamico.tools.DatamartManager;
import com.duodinamico.history.MatchingFinderForHistoryEvents;
import com.duodinamico.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.tools.PythonInvoker;
import com.duodinamico.tools.TaskScheduler;
import com.duodinamico.tools.ValuableContentMatcher;
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
    private final PythonInvoker pythonInvoker;

    public DatamartServiceController(ValuableContentMatcher valuableContentMatcher, MatchingFinderForHistoryEvents matchingFinderForHistoryEvents, DatamartManager datamartManager, EventListenerForRealTimeEvents eventListenerForRealTimeEvents, MatchingFinderForRealTimeEvents matchingFinderForRealTimeEvents, TaskScheduler taskScheduler, PythonInvoker pythonInvoker) {
        this.eventListenerForRealTimeEvents = eventListenerForRealTimeEvents;
        this.valuableContentMatcher = valuableContentMatcher;
        this.matchingFinderForHistoryEvents = matchingFinderForHistoryEvents;
        this.datamartManager = datamartManager;
        this.matchingFinderForRealTimeEvents = matchingFinderForRealTimeEvents;
        this.taskScheduler = taskScheduler;
        this.pythonInvoker = pythonInvoker;
    }

    public void execute() throws IOException, InterruptedException {
        //ºthis.datamartManager.deleteWholeDatamart();
        System.out.println("Bienvenido a FlightDelays®");
        System.out.println("----------------------------");
        //saveHistoryToDatamart();
        System.out.println("----------------------------");
        listenToRealTimeEvents();
        //this.pythonInvoker.executePythonScript();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable refreshDatamart = () -> {matchRealTimeEventsIfPossible();};
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

    public void matchRealTimeEventsIfPossible() {
        try {
            if (this.matchingFinderForRealTimeEvents.findPossibleMatchesForRealTimeEvents().size() != 0) {
                System.out.println("Actualizando Datamart a partir de eventos en tiempo real...");
                this.datamartManager.writeCleanContentToDatamart(this.valuableContentMatcher.mapToValuableContent(this.matchingFinderForRealTimeEvents.findPossibleMatchesForRealTimeEvents()));
                //this.pythonInvoker.executePythonScript();
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}