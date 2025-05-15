package com.duodinamico.tools;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public void programarTareaCadaMinuto(ScheduledExecutorService scheduler, Runnable tarea) {
        scheduler.scheduleAtFixedRate(tarea, 0, 60, TimeUnit.SECONDS);
    }
}
