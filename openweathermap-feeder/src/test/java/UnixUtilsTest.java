import com.duodinamico.openweathermapfeeder.tools.converters.UnixUtils;
import org.junit.Assert;
import org.junit.Test;

public class UnixUtilsTest {

    @Test
    public void findUnixOfYesterdayTest(){
        UnixUtils converter = new UnixUtils();
        System.out.println(converter.findUnixOfYesterday());
    }
}
