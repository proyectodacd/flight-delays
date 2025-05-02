import com.duodinamico.controller.WeatherResultMapper;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProvider;
import com.duodinamico.controller.persistency.Coordinates;
import com.duodinamico.controller.model.WeatherResult;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        setUpForRegularCase();
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider(apiKey[0]);
        WeatherResultMapper weatherResultMapper = new WeatherResultMapper();
        WeatherResult weatherExample = weatherResultMapper.getWeatherResult(openWeatherMapProvider.weatherProvider(new Coordinates(40.8291,-74.1574), String.valueOf(1743529579)));
        assertTrue(weatherExample instanceof WeatherResult);
        System.out.println(weatherExample.getTemperature());
        System.out.println(weatherExample.getWeatherDescription());
    }










}
