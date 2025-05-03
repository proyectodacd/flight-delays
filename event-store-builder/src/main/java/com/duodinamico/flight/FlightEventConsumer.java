package com.duodinamico.flight;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventDeserializer;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class FlightEventConsumer {

    private final String url;
    private final String topicName = "Flights";
    private final String clientID = "event-store-consumer";
    private final FlightEventDeserializer deserializer = new FlightEventDeserializer();
    private final FlightEventStorage storage = new FlightEventStorage(new EventsFilePathGenerator());

    public FlightEventConsumer(String url) {
        this.url = url;
    }

    public void consumeFlightEvents() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageConsumer consumer = session.createDurableSubscriber(topic, "flight-subscription");

        System.out.println("FlightEventConsumer started...");

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                try {
                    String json = ((TextMessage) message).getText();
                    FlightEvent event = deserializer.deserializeFlightEvent(json);
                    storage.saveToEventsFile(json);
                    System.out.println("Mensaje guardado: " + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
