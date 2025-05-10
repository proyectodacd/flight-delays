import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProcessor;
import org.junit.Before;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviationStackProcessorTest {

    private String fileContent;
    private String[] apiKeys;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeys.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @org.junit.Test
    public void aviationStackProcessorTestNoInvalidKeys() throws Exception {
        setUpForRegularCase();
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(apiKeys);
        String result = aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition("dep_iata","MAD"),"dep_iata","MAD");
        assertTrue(result instanceof String);
        System.out.println(result);
        System.out.println(aviationStackProcessor.getApiKeyList()[aviationStackProcessor.getCurrentKeyNumber()]);
    }

    @Before
    public void setUpForWeirdCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeysFake.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @org.junit.Test
    public void aviationStackProcessorTestWithInvalidKeys() throws Exception {
        setUpForWeirdCase();
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(apiKeys);
        String result = aviationStackProcessor.petitionValidator(aviationStackProcessor.flightsPetition("arr_iata","MAD"),"arr_iata","MAD");
        assertTrue(result instanceof String);
        System.out.println(result);
        System.out.println(aviationStackProcessor.getApiKeyList()[aviationStackProcessor.getCurrentKeyNumber()]);
    }
}
