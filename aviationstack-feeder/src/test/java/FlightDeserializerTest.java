import com.duodinamico.controller.FlightMapper;
import com.duodinamico.controller.apiconsumer.FlightDeserializer;
import com.duodinamico.model.FlightModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FlightDeserializerTest {

    FlightMapper mapper = new FlightMapper();

    private final String json = "{\n" +
            "    \"pagination\": {\n" +
            "        \"limit\": 100,\n" +
            "        \"offset\": 0,\n" +
            "        \"count\": 100,\n" +
            "        \"total\": 1669022\n" +
            "    },\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"flight_date\": \"2019-12-12\",\n" +
            "            \"flight_status\": \"active\",\n" +
            "            \"departure\": {\n" +
            "                \"airport\": \"San Francisco International\",\n" +
            "                \"timezone\": \"America/Los_Angeles\",\n" +
            "                \"iata\": \"SFO\",\n" +
            "                \"icao\": \"KSFO\",\n" +
            "                \"terminal\": \"2\",\n" +
            "                \"gate\": \"D11\",\n" +
            "                \"delay\": 13,\n" +
            "                \"scheduled\": \"2019-12-12T04:20:00+00:00\",\n" +
            "                \"estimated\": \"2019-12-12T04:20:00+00:00\",\n" +
            "                \"actual\": \"2019-12-12T04:20:13+00:00\",\n" +
            "                \"estimated_runway\": \"2019-12-12T04:20:13+00:00\",\n" +
            "                \"actual_runway\": \"2019-12-12T04:20:13+00:00\"\n" +
            "            },\n" +
            "            \"arrival\": {\n" +
            "                \"airport\": \"Dallas/Fort Worth International\",\n" +
            "                \"timezone\": \"America/Chicago\",\n" +
            "                \"iata\": \"DFW\",\n" +
            "                \"icao\": \"KDFW\",\n" +
            "                \"terminal\": \"A\",\n" +
            "                \"gate\": \"A22\",\n" +
            "                \"baggage\": \"A17\",\n" +
            "                \"delay\": 0,\n" +
            "                \"scheduled\": \"2019-12-12T04:20:00+00:00\",\n" +
            "                \"estimated\": \"2019-12-12T04:20:00+00:00\",\n" +
            "                \"actual\": null,\n" +
            "                \"estimated_runway\": null,\n" +
            "                \"actual_runway\": null\n" +
            "            },\n" +
            "            \"airline\": {\n" +
            "                \"name\": \"American Airlines\",\n" +
            "                \"iata\": \"AA\",\n" +
            "                \"icao\": \"AAL\"\n" +
            "            },\n" +
            "            \"flight\": {\n" +
            "                \"number\": \"1004\",\n" +
            "                \"iata\": \"AA1004\",\n" +
            "                \"icao\": \"AAL1004\",\n" +
            "                \"codeshared\": null\n" +
            "            },\n" +
            "            \"aircraft\": {\n" +
            "               \"registration\": \"N160AN\",\n" +
            "               \"iata\": \"A321\",\n" +
            "               \"icao\": \"A321\",\n" +
            "               \"icao24\": \"A0F1BB\"\n" +
            "            },\n" +
            "            \"live\": {\n" +
            "                \"updated\": \"2019-12-12T10:00:00+00:00\",\n" +
            "                \"latitude\": 36.28560000,\n" +
            "                \"longitude\": -106.80700000,\n" +
            "                \"altitude\": 8846.820,\n" +
            "                \"direction\": 114.340,\n" +
            "                \"speed_horizontal\": 894.348,\n" +
            "                \"speed_vertical\": 1.188,\n" +
            "                \"is_ground\": false\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test
    public void deserialize() {
        FlightDeserializer deserializer = new FlightDeserializer();
        ArrayList<FlightModel> flightList = deserializer.flightDeserializer(json);
        FlightModel flightExample = flightList.get(0);
        Assert.assertEquals("2019-12-12", flightExample.getFlightDate());
        Assert.assertEquals("active", flightExample.getFlightStatus());
        Assert.assertEquals("San Francisco International", flightExample.getDepartureAirport());
        Assert.assertEquals("America/Los_Angeles", flightExample.getDepartureTimezone());
        Assert.assertEquals("SFO", flightExample.getDepartureIata());


    }







}
