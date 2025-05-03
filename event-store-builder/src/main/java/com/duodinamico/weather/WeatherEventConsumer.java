package com.duodinamico.weather;

import com.duodinamico.controller.eventintegration.WeatherEvent;
import com.duodinamico.controller.eventintegration.WeatherEventDeserializer;
import com.duodinamico.flight.EventsFilePathGeneratorForWriting;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class WeatherEventConsumer {

    private final String url;
    private final String topicName = "Weather";
    private final String clientID = "event-store-consumer";
    private final WeatherEventDeserializer deserializer = new WeatherEventDeserializer();
    private final WeatherEventStorage storage = new WeatherEventStorage(new EventsFilePathGeneratorForWriting());

    public WeatherEventConsumer(String url) {
        this.url = url;
    }

    public void consumeWeatherEvents() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = factory.createConnection();
        connection.setClientID(clientID);
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(topicName);
        MessageConsumer consumer = session.createDurableSubscriber(topic, "weather-subscription");

        System.out.println("WeatherEventConsumer started...");

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                try {
                    String json = ((TextMessage) message).getText();
                    WeatherEvent event = deserializer.deserializeWeatherEvent(json);
                    storage.saveWeatherToEventsFile(json, event);
                    System.out.println("Mensaje guardado: " + json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
