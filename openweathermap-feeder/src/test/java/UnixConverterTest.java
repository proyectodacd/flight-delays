import com.duodinamico.controller.persistency.UnixConverter;
import org.junit.Assert;
import org.junit.Test;

public class UnixConverterTest {
    @Test
    public void convertToUnixTest(){
        UnixConverter converter = new UnixConverter();
        int result = converter.convertToUnix("2025-03-28 00:35:00","Asia/Singapore");
        Assert.assertEquals(1743093300+600, result);
    }
}
