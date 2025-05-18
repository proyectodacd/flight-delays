package com.duodinamico.flightdelayestimator.infrastructure.datamart.realtime.storage;

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

        Session session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("Datamart ahora escuchando a los topics: " + topicNames);
        System.out.println("-----------------------------------------------------------------------");

        for (String topicName : topicNames) {
            MessageConsumer consumer = getMessageConsumer(session, topicName);
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        saveMessage((TextMessage) message,topicName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public Connection getConnection() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(this.clientID);
        connection.start();
        return connection;
    }

    public MessageConsumer getMessageConsumer(Session session, String topicName) throws JMSException {
        Topic topic = session.createTopic(topicName);
        String subscriptionName = topicName + "-subscription for Datamart";
        MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);
        return consumer;
    }

    public void saveMessage (TextMessage message, String topicName) throws JMSException {
        String json = message.getText();
        this.eventStorage.saveRealTimeEventToDatamart(json, topicName);
        System.out.println("Mensaje guardado en Datamart, de [" + topicName + "]: " + json);
    }
}
