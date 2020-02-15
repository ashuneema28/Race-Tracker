import message.Message;
import org.junit.Test;
import raceData.Athlete;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class SubscribeMessageTest {

    @Test
    public void testConstruction() throws Exception{

        TrackingServer trackingServer = new TrackingServer(12037);

        String messagereceived = "Hello,127.0.0.1,30003";
        Message message1 = Message.createObject(messagereceived);

        message1.execute(trackingServer,30003);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30003));

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");
        trackingServer.addAthlete(athlete1);

        String message = "Subscribe,1";
        message1 = Message.createObject(message);

        message = "Subscribe";
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

        TrackingServer trackingServer = new TrackingServer(12038);

        String message = "Hello,127.0.0.1,30002";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer,30002);
        assertEquals(trackingServer.getClientList().get(0),trackingServer.getClientByPortNumber(30002));

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        trackingServer.addAthlete(athlete1);

        message = "Subscribe,1";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30002);
        assertEquals(athlete1.getClientList().get(0),trackingServer.getClientByPortNumber(30002));

    }
}
