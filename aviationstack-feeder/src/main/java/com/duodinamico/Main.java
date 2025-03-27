package com.duodinamico;

import com.duodinamico.controller.FlightController;

public class Main {
    public static void main(String[] args) {
        FlightController flightController = new FlightController();
        flightController.execute(args);
    }
}
