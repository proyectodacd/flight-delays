package com.duodinamico.flightdelayestimator.tools;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public void scheduleTask(ScheduledExecutorService scheduler, Runnable tarea, int frecuencia, int delay) {
        scheduler.scheduleAtFixedRate(tarea, delay, frecuencia, TimeUnit.SECONDS);
    }
}
