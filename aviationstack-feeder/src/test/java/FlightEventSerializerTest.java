import com.duodinamico.controller.apiconsumer.AviationStackProcessor;
import com.duodinamico.controller.apiconsumer.AviationStackProvider;
import com.duodinamico.controller.eventIntegration.FlightEvent;
import com.duodinamico.controller.eventIntegration.FlightEventSerializer;
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
        ArrayList<FlightEvent> flightEvents = aviationStackProvider.flightEventProvider();
        Assert.assertTrue(flightEventSerializer.serializeFlightEvent(flightEvents.getFirst()) instanceof String);
        System.out.println(flightEventSerializer.serializeFlightEvent(flightEvents.getFirst()));
    }
}
