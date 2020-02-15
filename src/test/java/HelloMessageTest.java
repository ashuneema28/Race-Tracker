import message.Message;
import org.junit.Assert;
import org.junit.Test;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class HelloMessageTest {
    @Test
    public void testConstruction() throws Exception{

        TrackingServer trackingServer = new TrackingServer(12035);

        String message = "Hello,127.0.0.1,30000";
        Message message1 = Message.createObject(message);

        message = "Hello";
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

        TrackingServer trackingServer = new TrackingServer(12036);

        String message = "Hello,127.0.0.1,30001";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30001);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30001));

        message = "Race,Marathon,12345";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30001);

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30006);

        message = "Hello,127.0.0.1,30007";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30007);
        assertEquals(trackingServer.getClientList().get(1),trackingServer.getClientByPortNumber(30007));
    }
}

