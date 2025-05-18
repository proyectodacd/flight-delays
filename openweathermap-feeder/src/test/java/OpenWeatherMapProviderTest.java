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
    private String[] apiKey;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("apikey.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKey = fileContent.split("\\s+");
    }

    @Test
    public void openWeatherMapProviderTest() throws Exception {
//        setUpForRegularCase();
//        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider(apiKey[0]);
//        WeatherEventMapper weatherEventMapper = new WeatherEventMapper();
//        ArrayList<WeatherEvent> weatherEvents = weatherEventMapper.getWeatherEvent(openWeatherMapProvider.weatherProvider(new Coordinates(40.8291,-74.1574), String.valueOf(1743529579)),"MAD");
//        System.out.println(weatherEvents.getFirst().getTemperature());

    }










}
