import com.duodinamico.controller.apiconsumer.AviationStackProcessor;
import com.duodinamico.controller.apiconsumer.OpenWeatherMapProcessor;
import com.duodinamico.controller.persistency.Coordinates;
import org.junit.Before;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenWeatherMapProcessorTest {

    private String fileContent;
    private String[] apiKey;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("apikey.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKey = fileContent.split("\\s+");
    }

    @org.junit.Test
    public void openWeatherMapProcessorTest() throws Exception {
        setUpForRegularCase();
        OpenWeatherMapProcessor openWeatherMapProcessor = new OpenWeatherMapProcessor(apiKey[0]);
        assertTrue(openWeatherMapProcessor.weatherPetition(new Coordinates(40.8291,-74.1574),"1743529579") instanceof String);
        System.out.println(openWeatherMapProcessor.weatherPetition(new Coordinates(40.8291,-74.1574),"1743529579"));
        System.out.println(openWeatherMapProcessor.getApiKey());
    }





}
