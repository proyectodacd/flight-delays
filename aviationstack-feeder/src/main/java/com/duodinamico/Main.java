package com.duodinamico;

import com.duodinamico.controller.FlightController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        FlightController controller = new FlightController(Arrays.copyOfRange(args,2,args.length), args[0], args[1]);
        controller.executeEventSender();
    }
}
