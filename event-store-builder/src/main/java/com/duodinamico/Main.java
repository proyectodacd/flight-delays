package com.duodinamico;

import jakarta.jms.JMSException;

public class Main {
    public static void main(String[] args) {
        FlightEventConsumer consumer = new FlightEventConsumer();
        try {
            consumer.consumeFlightEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
