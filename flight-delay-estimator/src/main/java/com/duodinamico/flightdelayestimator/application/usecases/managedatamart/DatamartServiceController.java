package com.duodinamico.flightdelayestimator.application.usecases.managedatamart;

import com.duodinamico.flightdelayestimator.infrastructure.datamart.DatamartManager;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.history.MatchingFinderForHistoryEvents;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.processing.MatchingFinderForRealTimeEvents;
import com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.storage.EventListenerForRealTimeEvents;
import com.duodinamico.flightdelayestimator.tools.TaskScheduler;
import com.duodinamico.flightdelayestimator.tools.ValuableContentMatcher;

import com.google.gson.JsonObject;
import jakarta.jms.JMSException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
        this.datamartManager.deleteWholeDatamart();
        System.out.println("BIENVENIDO A FLIGHTDELAYS®");
        System.out.println("-----------------------------------------------------------------------");
        saveHistoryToDatamart();
        System.out.println("-----------------------------------------------------------------------");
        listenToRealTimeEvents();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        Runnable realTimeMatcher = matchRealTimeEvents();
        this.taskScheduler.scheduleTask(scheduler, realTimeMatcher, 120, 60);
    }

    public void saveHistoryToDatamart() {
        try {
            System.out.println("Cargando histórico de datos...");
            this.datamartManager.writeCleanContentToDatamart(this.valuableContentMatcher.matchCompatibleCouples(this.matchingFinderForHistoryEvents.findPossibleMatches()));
            System.out.println("Histórico de datos cargado con éxito.");
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

    public Runnable matchRealTimeEvents() {
        return () -> {
            try {
                List<Map<List<JsonObject>, List<JsonObject>>> temporaryExample = this.matchingFinderForRealTimeEvents.findPossibleMatchesForRealTimeEvents();
                this.datamartManager.writeCleanContentToDatamart(this.valuableContentMatcher.matchCompatibleCouples(temporaryExample));
            } catch (Exception e) {
                System.out.println("\n\nEventos recogidos en tiempo real insuficientes para hacer matching. (Próxima actualización en 2 minutos)");
            }
        };
    }
}