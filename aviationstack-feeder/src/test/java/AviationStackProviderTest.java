import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.aviationstackfeeder.domain.schema.FlightResponse;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
//        setUpForRegularCase();
//        AviationStackProvider aviationStackProvider = new AviationStackProvider(apiKeys);
//        assertTrue(aviationStackProvider.flightProvider("dep_iata","LPA") instanceof FlightResponse);
//        System.out.println(aviationStackProvider.flightProvider("dep_iata","LPA").toString());
    }

}
