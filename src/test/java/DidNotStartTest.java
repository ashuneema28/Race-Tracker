import message.Message;
import org.junit.Test;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class DidNotStartTest {
    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12053);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30019";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30019);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30019));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30019);

        message = "DidNotStart,1,0";
        message1 = Message.createObject(message);

        message = "DidNotStart";
        try {
            message1 = Message.createObject(message);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("1",e.getMessage());
        }
    }
    @Test
    public void testExecute()throws Exception{
        TrackingServer trackingServer = new TrackingServer(12054);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30020";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30020);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30020));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30020);

        message = "DidNotStart,1,0";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30020);
        assertEquals(trackingServer.getAthleteByBibNumber(1).getStatus(), "DidNotStart");
    }
}
