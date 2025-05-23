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
        Session session = getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("EventStoreBuilder ahora escuchando a los topics: " + this.topicNames);
        System.out.println("----------------------------");

        for (String topicName : this.topicNames) {
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
        String subscriptionName = topicName + "-subscription for EventStoreBuilder";
        MessageConsumer consumer = session.createDurableSubscriber(topic, subscriptionName);
        return consumer;
    }

    public void saveMessage (TextMessage message, String topicName) throws JMSException {
        String json = message.getText();
        this.eventStorage.saveToEventsFile(json, topicName);
        System.out.println("Mensaje guardado en EventStore, de [" + topicName + "]: " + json);
    }
}
