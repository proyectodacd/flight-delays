package com.duodinamico.eventstorebuilder.application.usecases.eventstorebuilder;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.List;
import java.util.UUID;

public class EventConsumer {

    private final String url;
    private final List<String> topicNames;
    private final String clientID = "event-store-builder-" + UUID.randomUUID();
    private final EventStorage eventStorage = new EventStorage(new EventsFilePathGeneratorForWriting());

    public EventConsumer(String url, List<String> topicNames) {
        this.url = url;
        this.topicNames = topicNames;
    }

    public void consumeEvents() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.println("EventStoreBuilder ahora escuchando a los topics: " + topicNames);
        System.out.println("----------------------------");

        for (String topicName : topicNames) {
            Topic topic = session.createTopic(topicName);
            String subscriptionName = topicName + "-subscription for EventStoreBuilder";

            MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);

            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        String json = ((TextMessage) message).getText();
                        eventStorage.saveToEventsFile(json, topicName);
                        System.out.println("Mensaje guardado en EventStore, de [" + topicName + "]: " + json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
