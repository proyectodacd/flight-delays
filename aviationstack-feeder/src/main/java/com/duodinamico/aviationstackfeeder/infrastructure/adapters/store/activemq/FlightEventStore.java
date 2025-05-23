package com.duodinamico.aviationstackfeeder.infrastructure.adapters.store.activemq;

import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.aviationstackfeeder.infrastructure.ports.FlightStore;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightEventMapper;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class FlightEventStore implements FlightStore{

    private final String url;
    private final String topicName;
    private FlightEventSerializer flightEventSerializer;
    private FlightEventMapper flightEventMapper;

    public FlightEventStore(String url, FlightEventSerializer flightEventSerializer, FlightEventMapper flightEventMapper) {
        this.url = url;
        this.topicName = "Flights";
        this.flightEventSerializer = flightEventSerializer;
        this.flightEventMapper = flightEventMapper;
    }

    @Override
    public void saveFlights(FlightResponse flightResponse) {

        ArrayList<FlightEvent> flightEvents = this.flightEventMapper.mapToFlightEvents(flightResponse);
        Connection connection = getConnection();
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            for (FlightEvent flightEvent : flightEvents) {
                System.out.println("Mensaje enviado: " + sendFlightEvent(flightEvent,session,producer).getText());
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

    public Connection getConnection() {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public TextMessage sendFlightEvent(FlightEvent flightEvent, Session session, MessageProducer producer) throws JMSException {
        String content = this.flightEventSerializer.serializeFlightEvent(flightEvent);
        TextMessage message = session.createTextMessage(content);
        producer.send(message);
        return message;
    }

}
