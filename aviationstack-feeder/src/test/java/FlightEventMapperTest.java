import com.duodinamico.aviationstackfeeder.domain.model.FlightEvent;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProcessor;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.FlightJSONParser;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightEventMapper;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightEventMapperTest {
    private String fileContent;
    private String[] apiKeys;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeys.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @Test
    public void flightEventMapperTest() throws Exception {
        setUpForRegularCase();
        String[] airports = {"LPA", "MAD", "BCN", "AMS"};
        AviationStackProcessor aviationStackProcessor = new AviationStackProcessor(apiKeys);
        FlightJSONParser flightDeserializer = new FlightJSONParser();
        AviationStackProvider aviationStackProvider = new AviationStackProvider(aviationStackProcessor, flightDeserializer, airports);
        FlightEventMapper flightEventMapper = new FlightEventMapper();
        ArrayList<FlightEvent> flightEvents = flightEventMapper.mapToFlightEvents(aviationStackProvider.flightProvider("dep_iata","LPA"));
        assertTrue(flightEvents instanceof ArrayList<FlightEvent>);
    }
}