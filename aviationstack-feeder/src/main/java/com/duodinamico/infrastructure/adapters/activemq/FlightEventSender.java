package com.duodinamico.infrastructure.adapters.activemq;

import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.ports.FlightStore;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.Flight;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;
import com.duodinamico.infrastructure.adapters.mappers.FlightEventMapper;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class FlightEventSender implements FlightStore<FlightEvent>{


    private final String url;
    private final String topicName = "Flights";
    private FlightEventSerializer flightEventSerializer = new FlightEventSerializer();
    private FlightEventMapper flightEventMapper = new FlightEventMapper();

    public FlightEventSender(String url) {
        this.url = url;
    }

    @Override
    public void saveFlights(FlightResponse flightResponse) {

        ArrayList<FlightEvent> flightEvents = flightEventMapper.mapToFlightEvents(flightResponse);

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



    @Override
    public ArrayList<FlightEvent> loadFlights() {
        return null;
    }


}
