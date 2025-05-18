import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProcessor;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.WeatherJSONParser;
import com.duodinamico.openweathermapfeeder.tools.converters.AirportToCoordinates;
import com.duodinamico.openweathermapfeeder.tools.mappers.WeatherEventMapper;
import com.duodinamico.openweathermapfeeder.infrastructure.adapters.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.openweathermapfeeder.domain.model.WeatherEvent;
import com.duodinamico.openweathermapfeeder.domain.model.Coordinates;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenWeatherMapProviderTest {

    private String fileContent;
    private String[] apiKeys;
    private String apiKey;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("apikey.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
        apiKey = apiKeys[0];
    }

    @Test
    public void openWeatherMapProviderTest() throws Exception {
        setUpForRegularCase();
        String[] airports = {"MAD", "BCN", "AMS", "LPA"};
        OpenWeatherMapProcessor openWeatherMapProcessor = new OpenWeatherMapProcessor(apiKey);
        WeatherJSONParser weatherDeserializer = new WeatherJSONParser();
        AirportToCoordinates airportToCoordinates = new AirportToCoordinates(apiKeys[1]);
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider(openWeatherMapProcessor, weatherDeserializer, airports);

        WeatherEventMapper weatherEventMapper = new WeatherEventMapper();
        ArrayList<WeatherEvent> weatherEvents = weatherEventMapper.getWeatherEvent(openWeatherMapProvider.provideWeather(airportToCoordinates.getAirportCoordinates("MAD"), String.valueOf(1743529578)),"MAD");
        System.out.println(weatherEvents.getFirst());

    }










}