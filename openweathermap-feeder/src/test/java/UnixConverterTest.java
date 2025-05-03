import com.duodinamico.controller.persistency.UnixConverter;
import org.junit.Assert;
import org.junit.Test;

public class UnixConverterTest {
    @Test
    public void convertToUnixTest(){
        UnixConverter converter = new UnixConverter();
        int result = converter.convertToUnix("2025-03-28T00:35+00:00");
        Assert.assertEquals(1743122700, result);
    }
}
