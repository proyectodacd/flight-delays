package com.duodinamico.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {
    public void programarTarea(ScheduledExecutorService scheduler, Runnable tarea, int hora, int minuto) {
        long delay = calcularTiempoHasta(hora, minuto);
        System.out.println("Programando tarea para las " + hora + ":" + minuto + " con delay de " + delay + " segundos.");
        scheduler.scheduleAtFixedRate(tarea, delay, 12 * 3600, TimeUnit.SECONDS);
    }

    public long calcularTiempoHasta(int hora, int minuto) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximo = ahora.withHour(hora).withMinute(minuto).withSecond(0);

        if (ahora.isAfter(proximo)) {
            proximo = proximo.plusDays(1);
        }

        return Duration.between(ahora, proximo).getSeconds();

    }
}
