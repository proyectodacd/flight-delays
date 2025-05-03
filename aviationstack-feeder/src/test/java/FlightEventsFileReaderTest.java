import com.duodinamico.domain.model.FlightEvent;
import com.duodinamico.infrastructure.adapters.activemq.EventsFilePathGenerator;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventSerializer;
import com.duodinamico.infrastructure.adapters.activemq.FlightEventsFileReader;
import com.duodinamico.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.infrastructure.adapters.mappers.FlightEventMapper;
import org.junit.Assert;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FlightEventsFileReaderTest {

    @org.junit.Test
    public void flightEventsFileReaderTest() throws Exception {
        FlightEventsFileReader reader = new FlightEventsFileReader();
        EventsFilePathGenerator eventsFilePathGenerator = new EventsFilePathGenerator();
        List<FlightEvent> flightEvents = reader.extractFlightEventsFromFile();


        Assert.assertNotNull(flightEvents);
        System.out.println(flightEvents.size());
        System.out.println(flightEvents.getFirst().getFlightIcao());
        System.out.println(flightEvents.getFirst().getTs());
        System.out.println(flightEvents.getFirst().getSs());
    }
}
