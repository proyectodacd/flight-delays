package com.duodinamico.controller.eventintegration;

import com.duodinamico.controller.WeatherEventMapper;
import com.duodinamico.controller.WeatherResultMapper;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.controller.persistency.WeatherStore;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.domain.model.FlightModel;
import com.duodinamico.infrastructure.adapters.apiconsumer.schema.FlightResponse;
import com.duodinamico.infrastructure.adapters.mappers.FlightModelMapper;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class WeatherEventSender implements WeatherStore {

    private final String url;
    private final String topicName = "Weather";
    private WeatherEventMapper weatherEventMapper;
    private AirportToCoordinates coordinates;
    private UnixConverter unixConverter;
    private OpenWeatherMapProvider openWeatherMapProvider;

    private WeatherEventSerializer weatherEventSerializer = new WeatherEventSerializer();

    public WeatherEventSender(String coordinatesDocumentation, String apiKey, String url) {
        this.url = url;
        this.weatherEventMapper = new WeatherEventMapper();
        this.coordinates = new AirportToCoordinates(coordinatesDocumentation);
        this.unixConverter = new UnixConverter();
        this.openWeatherMapProvider = new OpenWeatherMapProvider(apiKey);
    }

    @Override
    public void saveDepartureWeather(ArrayList<FlightEvent> flights) {
        for(FlightEvent flight : flights) {
            WeatherEvent weatherEvent = (coordinates.getAirportCoordinates(flight.getDepartureIata()) != null) ? weatherEventMapper.getWeatherEvent(flight, openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getDepartureIata()), String.valueOf(unixConverter.convertToUnix(flight.getEstimatedDepartureTime())))) : null;

            Connection connection = null;
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                connection = connectionFactory.createConnection();
                connection.start();


                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Topic topic = session.createTopic(topicName);
                MessageProducer producer = session.createProducer(topic);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);


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

    @Override
    public void saveArrivalWeather(ArrayList<FlightEvent> flights) {
        for(FlightEvent flight : flights) {
            WeatherEvent weatherEvent = (coordinates.getAirportCoordinates(flight.getArrivalIata()) != null) ? weatherEventMapper.getWeatherEvent(flight, openWeatherMapProvider.weatherProvider(coordinates.getAirportCoordinates(flight.getArrivalIata()),String.valueOf(unixConverter.convertToUnix(flight.getEstimatedArrivalTime())))) : null;

            Connection connection = null;
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                connection = connectionFactory.createConnection();
                connection.start();


                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Topic topic = session.createTopic(topicName);
                MessageProducer producer = session.createProducer(topic);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);


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
}