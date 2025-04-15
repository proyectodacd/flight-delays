package com.duodinamico;

import com.duodinamico.controller.WeatherController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarea2 = () -> {
            System.out.println("Ejecutando WeatherController a las: " + LocalDateTime.now());
            WeatherController controller = new WeatherController(args[0], args[1], args[2]);
            controller.execute();
        };

        programarTarea(scheduler, tarea2, 22, 34);

        try {
            scheduler.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void programarTarea(ScheduledExecutorService scheduler, Runnable tarea, int hora, int minuto) {
        long delay = calcularTiempoHasta(hora, minuto);
        System.out.println("Programando tarea para las " + hora + ":" + minuto + " con delay de " + delay + " segundos.");
        scheduler.scheduleAtFixedRate(tarea, delay, 12 * 3600, TimeUnit.SECONDS);
    }

    public static long calcularTiempoHasta(int hora, int minuto) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime proximo = ahora.withHour(hora).withMinute(minuto).withSecond(0);

        if (ahora.isAfter(proximo)) {
            proximo = proximo.plusDays(1);
        }

        return Duration.between(ahora, proximo).getSeconds();
    }
}
