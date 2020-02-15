import org.junit.Test;
import raceData.Client;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void testConstruction() throws Exception{

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12025);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12026);

        assertEquals(12025,client1.getPort());
        assertEquals(12026,client2.getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),client1.getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),client2.getAddress());

        client1.setPort(12031);
        client2.setPort(12032);

        assertEquals(12031,client1.getPort());
        assertEquals(12032,client2.getPort());

        client1.setAddress(InetAddress.getByName(client1.getAddress()).getHostAddress());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),client1.getAddress());
    }
}
