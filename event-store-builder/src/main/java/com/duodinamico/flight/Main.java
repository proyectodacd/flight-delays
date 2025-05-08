package com.duodinamico.flight;

import jakarta.jms.JMSException;

public class Main {
    public static void main(String[] args) {
        FlightEventConsumer consumer = new FlightEventConsumer(args[0]);
        try {
            consumer.consumeFlightEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
