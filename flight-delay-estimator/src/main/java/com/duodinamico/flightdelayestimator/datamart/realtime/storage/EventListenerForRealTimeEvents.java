package com.duodinamico.flightdelayestimator.datamart.realtime.storage;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.List;
import java.util.UUID;

public class EventListenerForRealTimeEvents {

    private final String url;
    private final List<String> topicNames;
    private final String clientID = "datamart-" + UUID.randomUUID();
    private final EventStorageForRealTimeEvents eventStorage;


    public EventListenerForRealTimeEvents(String url, List<String> topicNames, EventStorageForRealTimeEvents eventStorage) {
        this.url = url;
        this.topicNames = topicNames;
        this.eventStorage = eventStorage;
    }

    public void consumeRealTimeEvents() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.println("Datamart ahora escuchando a los topics: " + topicNames);
        System.out.println("----------------------------");

        for (String topicName : topicNames) {
            Topic topic = session.createTopic(topicName);
            String subscriptionName = topicName + "-subscription for Datamart";

            MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);

            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        String json = ((TextMessage) message).getText();
                        this.eventStorage.saveRealTimeEventToDatamart(json,topicName);
                        System.out.println("Mensaje guardado en Datamart, de [" + topicName + "]: " + json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
