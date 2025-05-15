import com.duodinamico.realtime.storage.CSVWriterForRealTimeEvents;
import org.junit.Test;

public class CSVWriterForRealTimeEventsTest {

    @Test
    public void getDateIDTest() {
        String json = "{\"ts\":\"2025-05-10T20:18:21.143268Z\",\"ss\":\"AviationStackFeeder\",\"flightIcao\":\"IBE5655\",\"flightDate\":\"2025-05-10\",\"flightStatus\":\"active\",\"departureAirport\":\"Prague Vaclav Havel Airport\",\"departureTimezone\":\"Europe/Prague\",\"departureIata\":\"PRG\",\"departureIcao\":\"LKPR\",\"departureDelay\":9,\"estimatedDepartureTime\":\"2025-05-10T20:40:00+00:00\",\"actualDepartureTime\":\"2025-05-10T20:49:00+00:00\",\"arrivalAirport\":\"El Prat De Llobregat\",\"arrivalTimezone\":\"Europe/Madrid\",\"arrivalIata\":\"BCN\",\"arrivalIcao\":\"LEBL\",\"arrivalDelay\":0,\"estimatedArrivalTime\":\"2025-05-10T23:15:00+00:00\",\"latitude\":0.0,\"longitude\":0.0,\"altitude\":0.0,\"horizontalSpeed\":0.0,\"verticalSpeed\":0.0,\"isOnGround\":false}\n";
        CSVWriterForRealTimeEvents csvWriter = new CSVWriterForRealTimeEvents();
        System.out.println(csvWriter.getDateID(json));
    }
}
