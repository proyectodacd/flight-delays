import com.duodinamico.controller.apiconsumer.WeatherDeserializer;
import com.duodinamico.controller.model.WeatherResult;
import org.junit.Assert;
import org.junit.Test;

public class WeatherDeserializerTest {
    private final String json = "{\n" +
            "   \"message\": \"Count: 24\",\n" +
            "   \"cod\": \"200\",\n" +
            "   \"city_id\": 4298960,\n" +
            "   \"calctime\": 0.00297316,\n" +
            "   \"cnt\": 24,\n" +
            "   \"list\": [\n" +
            "      {\n" +
            "         \"dt\": 1578384000,\n" +
            "         \"main\": {\n" +
            "            \"temp\": 275.45,\n" +
            "            \"feels_like\": 271.7,\n" +
            "            \"pressure\": 1014,\n" +
            "            \"humidity\": 74,\n" +
            "            \"temp_min\": 274.26,\n" +
            "            \"temp_max\": 276.48\n" +
            "         },\n" +
            "         \"wind\": {\n" +
            "            \"speed\": 2.16,\n" +
            "            \"deg\": 87\n" +
            "         },\n" +
            "         \"clouds\": {\n" +
            "            \"all\": 90\n" +
            "         },\n" +
            "         \"weather\": [\n" +
            "            {\n" +
            "               \"id\": 501,\n" +
            "               \"main\": \"Rain\",\n" +
            "               \"description\": \"moderate rain\",\n" +
            "               \"icon\": \"10n\"\n" +
            "            }\n" +
            "         ],\n" +
            "         \"rain\": {\n" +
            "            \"1h\": 0.9\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}\n";

    @Test
    public void deserializeWeatherTest() {
        WeatherDeserializer weatherDeserializer = new WeatherDeserializer();
        WeatherResult weatherResult = weatherDeserializer.weatherDeserializer(json);
        Assert.assertEquals(1578384000, weatherResult.getTime());
        Assert.assertEquals(275.45, weatherResult.getTemperature(), 0.001);
        Assert.assertEquals(271.7, weatherResult.getFeelsLike(), 0.001);
        Assert.assertEquals(2.16, weatherResult.getWindSpeed(), 0.001);
        Assert.assertEquals(87, weatherResult.getWindDirection());
        Assert.assertEquals(90, weatherResult.getPercentageOfClouds());
        Assert.assertEquals(0.9, weatherResult.getPrecipitation(), 0.001);
        Assert.assertEquals("moderate rain", weatherResult.getWeatherDescription());

    }



}
