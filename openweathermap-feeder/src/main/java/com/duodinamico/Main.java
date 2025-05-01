package com.duodinamico;

import com.duodinamico.controller.WeatherController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController(args[0], args[1], args[3], args[2]);
        controller.executeEventSender();

    }
}
