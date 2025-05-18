import com.duodinamico.aviationstackfeeder.domain.model.FlightModel;
import com.duodinamico.aviationstackfeeder.infrastructure.adapters.apiconsumer.AviationStackProvider;
import com.duodinamico.aviationstackfeeder.tools.mappers.FlightModelMapper;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightModelMapperTest {
    private String fileContent;
    private String[] apiKeys;

    @Before
    public void setUpForRegularCase() throws Exception {
        Path filePath = Paths.get(getClass().getClassLoader().getResource("ApiKeys.txt").toURI());
        fileContent = Files.readString(filePath);
        apiKeys = fileContent.split("\\s+");
    }

    @Test
    public void flightModelMapperTest() throws Exception {
//        setUpForRegularCase();
//        AviationStackProvider aviationStackProvider = new AviationStackProvider(apiKeys);
//        FlightModelMapper flightModelMapper = new FlightModelMapper();
//        ArrayList<FlightModel> flightModelList = flightModelMapper.mapToFlightModels(aviationStackProvider.flightProvider("dep_iata","LPA"));
//        assertTrue(flightModelList instanceof ArrayList<FlightModel>);
//        FlightModel firstFlight = flightModelList.get(0);
//        System.out.println(firstFlight.getFlightIcao());
//        System.out.println(firstFlight.getFlightDate());
    }
}
