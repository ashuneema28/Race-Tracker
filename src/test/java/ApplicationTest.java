import org.junit.Test;
import raceServer.Application;
import raceServer.TrackingServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ApplicationTest {
    @Test
    public void testMain() throws Exception {
        String arguments[] = {"12090"};
        Application.main(arguments);

        arguments = new String[]{"1","2"};
        Application.main((arguments));

    }
}
