package com.duodinamico;

import com.duodinamico.controller.WeatherController;

public class Main {
    public static void main(String[] args) {
        WeatherController controller = new WeatherController();
        controller.execute(args);
    }
}
