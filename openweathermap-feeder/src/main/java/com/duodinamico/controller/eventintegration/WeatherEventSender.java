package com.duodinamico.controller.eventintegration;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class WeatherEventSender {

    private final String url;

    private final String topicName = "Weather";

    private WeatherEventSerializer weatherEventSerializer = new WeatherEventSerializer();

    public WeatherEventSender(String url) {
        this.url = url;
    }

    public void sendWeatherEvents(WeatherEvent weatherEvent) {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();


            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            System.out.println("topic created");


            String content = weatherEventSerializer.serializeWeatherEvent(weatherEvent);
            TextMessage message = session.createTextMessage(content);
            producer.send(message);
            System.out.println("Mensaje enviado: " + message.getText());


        } catch (JMSException e) {
            System.err.println("Error al enviar mensajes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

}