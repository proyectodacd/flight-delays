package com.duodinamico.openweathermapfeeder.tools.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    public void scheduleTask(ScheduledExecutorService scheduler, Runnable task, int hour, int minute) {
        long delay = calculateDelay(hour, minute);
        System.out.println("Programando tarea para las " + hour + ":" + minute + " con delay de " + delay + " segundos.");
        scheduler.scheduleAtFixedRate(task, delay, 12 * 3600, TimeUnit.SECONDS);
    }

    public long calculateDelay(int hour, int minute) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximo = ahora.withHour(hour).withMinute(minute).withSecond(0);

        if (ahora.isAfter(proximo)) {
            proximo = proximo.plusDays(1);
        }

        return Duration.between(ahora, proximo).getSeconds();

    }

}
