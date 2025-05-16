package com.duodinamico.eventstorebuilder;

import com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager.EventConsumer;
import com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager.EventStorage;
import com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager.EventsFilePathGenerator;
import jakarta.jms.JMSException;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventConsumer consumer = new EventConsumer(args[0], List.of(Arrays.copyOfRange(args, 1, args.length)), new EventStorage(new EventsFilePathGenerator()));
        try {
            consumer.consumeEvents();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
