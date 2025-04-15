import com.duodinamico.controller.persistency.AirportToCoordinates;
import com.duodinamico.controller.persistency.Coordinates;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirportToCoordinatesTest {

    public String filepath;
    public String fileContent;


    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("apikey.txt").toURI());
        fileContent = Files.readString(filePath);
        filepath = fileContent.split("\\s+")[1];
    }

    @Test
    public void airportToCoordinatesTest() throws Exception {
        setUpForRegularCase();
        AirportToCoordinates airportToCoordinates = new AirportToCoordinates(filepath);
        assertTrue(airportToCoordinates.getAirportCoordinates("JFK") instanceof Coordinates);
        System.out.println(airportToCoordinates.getAirports().get("JFK").getLatitude());
        System.out.println(airportToCoordinates.getAirports().get("JFK").getLongitude());
        System.out.println(airportToCoordinates.getAirports().size());
    }




}
