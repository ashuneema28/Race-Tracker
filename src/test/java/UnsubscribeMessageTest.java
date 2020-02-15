import message.Message;
import org.junit.Test;
import raceData.Athlete;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class UnsubscribeMessageTest {
    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12043);

        String message = "Hello,127.0.0.1,30008";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30008);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30008));

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        trackingServer.addAthlete(athlete1);

        message = "Subscribe,1";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30008);
        assertEquals(athlete1.getClientList().get(0),trackingServer.getClientByPortNumber(30008));

        message = "Unsubscribe,1";
        message1 = Message.createObject(message);

        message = "Unsubscribe";
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
        TrackingServer trackingServer = new TrackingServer(12044);

        String message = "Hello,127.0.0.1,30009";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30009);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30009));

        message = "Hello,127.0.0.1,30010";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30010);
        assertEquals(trackingServer.getClientList().get(1),trackingServer.getClientByPortNumber(30010));

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        trackingServer.addAthlete(athlete1);

        message = "Subscribe,1";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30009);
        assertEquals(athlete1.getClientList().get(0),trackingServer.getClientByPortNumber(30009));

        message = "Subscribe,1";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30010);
        assertEquals(athlete1.getClientList().get(1),trackingServer.getClientByPortNumber(30010));

        message = "Unsubscribe,1";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30009);
        assertEquals(athlete1.getClientList().get(0),trackingServer.getClientByPortNumber(30010));
    }
}
