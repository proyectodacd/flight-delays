import com.duodinamico.controller.persistency.UnixToUTCDateFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnixToUTCDateFormatterTest {

    @Test
    public void unixToUTCDateFormatter() {
        UnixToUTCDateFormatter formatter = new UnixToUTCDateFormatter();
        assertEquals("2025-05-08T00:00:00+00:00",formatter.formatUnixSecondsToISO8601UTC(1746662400));
    }
}


