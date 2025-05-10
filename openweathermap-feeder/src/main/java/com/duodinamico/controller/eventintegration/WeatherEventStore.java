package com.duodinamico.controller.eventintegration;

import com.duodinamico.controller.WeatherEventMapper;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.apiconsumer.schema.WeatherResponse;
import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.UnixConverter;
import com.duodinamico.controller.persistency.WeatherStore;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class WeatherEventStore implements WeatherStore {

    private final String url;
    private final String topicName = "Weather";
    private WeatherEventMapper weatherEventMapper;
    private WeatherEventSerializer weatherEventSerializer = new WeatherEventSerializer();

    public WeatherEventStore(String url) {
        this.url = url;
        this.weatherEventMapper = new WeatherEventMapper();
    }

    @Override
    public void saveWeather(WeatherResponse weatherResponse, String city) {
        ArrayList<WeatherEvent> weatherEvents = this.weatherEventMapper.getWeatherEvent(weatherResponse, city);
            Connection connection = null;
            try {
                ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                connection = connectionFactory.createConnection();
                connection.start();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Topic topic = session.createTopic(topicName);
                MessageProducer producer = session.createProducer(topic);
                producer.setDeliveryMode(DeliveryMode.PERSISTENT);

                for (WeatherEvent weatherEvent : weatherEvents) {
                    String content = weatherEventSerializer.serializeWeatherEvent(weatherEvent);
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