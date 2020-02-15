import message.Message;
import org.junit.Test;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.InetAddress;
import java.net.SocketException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class MessageTest {
    @Test
    public void TestConstruction_And_CreateObject() throws Exception{

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12033);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12034);

        String message = "Hello,"+client1.getAddress()+","+ String.valueOf(client1.getPort());
        Message message1 = Message.createObject(message);

        message = "Race,Bension Loop,16090";
        message1 = Message.createObject(message);

        message ="Registered,1,0,Valentine,Zamora,M,30";
        message1 =Message.createObject(message);

        message = "Started,1,150";
        message1 = Message.createObject(message);

        message = "OnCourse,1,180,260.143548";
        message1 = Message.createObject(message);

        message = "Subscribe,1";
        message1 = Message.createObject(message);

        message = "Unsubscribe,1";
        message1 = Message.createObject(message);

        message = "Finished,6,2328";
        message1 = Message.createObject(message);

        message = "DidNotFinish,6,180";
        message1 = Message.createObject(message);

        message = "DidNotStart,6,0";
        message1 = Message.createObject(message);

        message = "DidNotStart";
        try{
            message = "DidNotStart";
            message1 = Message.createObject(message);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("1", e.getMessage());
        }

        message = "How do you do";
        message1 = Message.createObject(message);
    }
}
