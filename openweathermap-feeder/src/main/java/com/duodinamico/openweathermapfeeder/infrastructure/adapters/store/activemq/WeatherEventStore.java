package com.duodinamico.openweathermapfeeder.infrastructure.adapters.store.activemq;

import com.duodinamico.openweathermapfeeder.tools.mappers.WeatherEventMapper;
import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.openweathermapfeeder.domain.schema.WeatherResponse;
import com.duodinamico.openweathermapfeeder.infrastructure.ports.WeatherStore;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;

public class WeatherEventStore implements WeatherStore {

    private final String url;
    private final String topicName;
    private WeatherEventMapper weatherEventMapper;
    private WeatherEventSerializer weatherEventSerializer;

    public WeatherEventStore(String url, WeatherEventMapper weatherEventMapper, WeatherEventSerializer weatherEventSerializer) {
        this.url = url;
        this.topicName = "Weather";
        this.weatherEventMapper = weatherEventMapper;
        this.weatherEventSerializer = weatherEventSerializer;
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