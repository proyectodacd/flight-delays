package com.duodinamico.weather;

import com.duodinamico.flight.FlightEventConsumer;
import jakarta.jms.JMSException;

public class Main {
    public static void main(String[] args) {
        WeatherEventConsumer consumer = new WeatherEventConsumer(args[0]);
        try {
            consumer.consumeWeatherEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
