package com.duodinamico.eventstorebuilder.application.usecases.eventstorebuildermanager;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.List;
import java.util.UUID;

public class EventConsumer {

    private final String url;
    private final List<String> topicNames;
    private final String clientID;
    private final EventStorage eventStorage;

    public EventConsumer(String url, List<String> topicNames, EventStorage eventStorage) {
        this.url = url;
        this.topicNames = topicNames;
        this.clientID = "event-store-builder-" + UUID.randomUUID();
        this.eventStorage = eventStorage;
    }

    public void consumeEvents() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(this.clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.println("EventStoreBuilder ahora escuchando a los topics: " + this.topicNames);
        System.out.println("----------------------------");

        for (String topicName : this.topicNames) {
            Topic topic = session.createTopic(topicName);
            String subscriptionName = topicName + "-subscription for EventStoreBuilder";

            MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);

            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        String json = ((TextMessage) message).getText();
                        this.eventStorage.saveToEventsFile(json, topicName);
                        System.out.println("Mensaje guardado en EventStore, de [" + topicName + "]: " + json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
