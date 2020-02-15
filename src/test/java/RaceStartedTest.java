import message.Message;
import org.junit.Test;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RaceStartedTest {
    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12039);

        String message = "Hello,127.0.0.1,30003";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30003);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30003));

        message = "Race,Marathon,12345";
        message1 = Message.createObject(message);

        message = "Race";
        try {
            message1 = Message.createObject(message);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("2",e.getMessage());
        }

    }

    @Test
    public void testExecute() throws Exception{

        TrackingServer trackingServer = new TrackingServer(12040);

        String message = "Hello,127.0.0.1,30004";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30004));

        message = "Race,Marathon,12345";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);
    }
}
