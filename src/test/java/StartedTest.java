import message.Message;
import org.junit.Test;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class StartedTest {
    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12045);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30011";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30011);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30011));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30011);

        message = "Started,1,150";
        message1 = Message.createObject(message);

        message = "Started";
        try {
            message1 = Message.createObject(message);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("1",e.getMessage());
        }
    }

    @Test
    public void testExecute() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12046);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30012";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30012);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30012));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30012);

        message = "Started,1,150";
        message1 = Message.createObject(message);
        message1.execute(trackingServer,30012);

        assertEquals(trackingServer.getAthleteByBibNumber(1).getStatus(),"Started");
    }
}
