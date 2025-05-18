import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProcessor;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.FlightJSONParser;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviationStackProviderTest {

    private String fileContent;
    private String[] apiKeys;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeys.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @Test
    public void aviationStackProviderTest() throws Exception {
        setUpForRegularCase();
        String[] airports = {"LPA", "MAD", "BCN", "AMS"};
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(apiKeys);
        FlightJSONParser flightDeserializer = new FlightJSONParser();
        AviationStackProvider aviationStackProvider = new AviationStackProvider(aviationStackProcessor, flightDeserializer, airports);
        assertTrue(aviationStackProvider.flightProvider("dep_iata","LPA") instanceof FlightResponse);
        System.out.println(aviationStackProvider.flightProvider("dep_iata","LPA").toString());
    }

}