import message.Message;
import org.junit.Test;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class RegisteredMessageTest {
    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12041);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30005";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30005);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30005));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message = "Registered";
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
        TrackingServer trackingServer = new TrackingServer(12042);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30004);

        message = "Hello,127.0.0.1,30006";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30006);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30006));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30006);
    }
}
