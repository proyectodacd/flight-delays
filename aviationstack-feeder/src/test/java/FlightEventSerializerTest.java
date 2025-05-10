import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventSerializer;
import com.duodinamico.infrastructure.adapters.mappers.FlightEventMapper;
import org.junit.Assert;
import org.junit.Before;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightEventSerializerTest {

    private String fileContent;
    private String[] apiKeys;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeys.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @org.junit.Test
    public void flightEventSerializerTest() throws Exception {
        setUpForRegularCase();
        AviationStackProvider aviationStackProvider = new AviationStackProvider(apiKeys);
        FlightEventSerializer flightEventSerializer = new FlightEventSerializer();
        FlightEventMapper flightEventMapper = new FlightEventMapper();
        ArrayList<FlightEvent> flightEvents = flightEventMapper.mapToFlightEvents(aviationStackProvider.flightProvider("dep_iata","LPA"));
        Assert.assertTrue(flightEventSerializer.serializeFlightEvent(flightEvents.getFirst()) instanceof String);
        System.out.println(flightEventSerializer.serializeFlightEvent(flightEvents.getFirst()));
    }
}
