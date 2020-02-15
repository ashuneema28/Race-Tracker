import message.Message;
import org.junit.Test;
import raceServer.TrackingServer;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class OnCourseUpdateTest {
    @Test
    public void testConstruction() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12047);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer, 30004);

        message = "Hello,127.0.0.1,30013";
        message1 = Message.createObject(message);

        message1.execute(trackingServer, 30013);
        assertEquals(trackingServer.getClientList().get(0), trackingServer.getClientByPortNumber(30013));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer, 30013);

        message = "Started,1,150";
        message1 = Message.createObject(message);
        message1.execute(trackingServer, 30013);

        assertEquals(trackingServer.getAthleteByBibNumber(1).getStatus(), "Started");

        message = "OnCourse,1,180,260.143548";
        message1 = Message.createObject(message);

        message = "OnCourse";
        try {
            message1 = Message.createObject(message);
            fail("Expected exception not thrown");
        } catch (Exception e) {
            assertEquals("1", e.getMessage());
        }
    }

    @Test
    public void testExecute() throws Exception {
        TrackingServer trackingServer = new TrackingServer(12048);

        String message = "Race,Marathon,12345";
        Message message1 = Message.createObject(message);

        message1.execute(trackingServer, 30004);

        message = "Hello,127.0.0.1,30014";
        message1 = Message.createObject(message);

        message1.execute(trackingServer, 30014);
        assertEquals(trackingServer.getClientList().get(0), trackingServer.getClientByPortNumber(30014));

        message = "Registered,1,0,Valentine,Zamora,M,30";
        message1 = Message.createObject(message);

        message1.execute(trackingServer, 30014);

        message = "Started,1,150";
        message1 = Message.createObject(message);
        message1.execute(trackingServer, 30014);

        assertEquals(trackingServer.getAthleteByBibNumber(1).getStatus(), "Started");

        message = "OnCourse,1,180,260.143548";
        message1 = Message.createObject(message);

        message1.execute(trackingServer,30014);

        assertEquals(trackingServer.getAthleteByBibNumber(1).getStatus(), "OnCourse");

    }
}
