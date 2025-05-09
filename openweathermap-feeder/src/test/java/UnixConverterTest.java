import com.duodinamico.controller.persistency.UnixConverter;
import org.junit.Assert;
import org.junit.Test;

public class UnixConverterTest {
    @Test
    public void convertToUnixTest(){
        UnixConverter converter = new UnixConverter();
        int result = converter.convertToUnix("2025-05-08T00:00+00:00");
        Assert.assertEquals(1746662400, result);
    }

    @Test
    public void findUnixOfYesterdayTest(){
        UnixConverter converter = new UnixConverter();
        converter.findUnixOfYesterday();
    }
}
