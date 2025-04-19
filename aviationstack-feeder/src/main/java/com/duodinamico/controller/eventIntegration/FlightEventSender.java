package com.duodinamico.controller.eventIntegration;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class FlightEventSender {

    // URL del broker JMS
    private final String url = "tcp://localhost:61616";

    // Nombre del Topic
    private final String topicName = "Flights";

    private FlightEventSerializer flightEventSerializer = new FlightEventSerializer();

    public void sendFlightEvents(ArrayList<FlightEvent> flightEvents) {
        Connection connection = null;

        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            for (FlightEvent flightEvent : flightEvents) {
                String content = flightEventSerializer.serializeFlightEvent(flightEvent);
                TextMessage message = session.createTextMessage(content);
                producer.send(message);
                System.out.println("Mensaje enviado: " + message.getText());
            }

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
