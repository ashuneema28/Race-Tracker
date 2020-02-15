import org.junit.Test;
import raceData.Athlete;
import raceData.Client;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class AthleteTest {

    @Test
    public void testConstruction(){
        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        assertEquals("Registered",athlete1.getStatus());
        assertNotNull(athlete1.getStatus());

        assertEquals(1,athlete1.getBibNumber());

        assertEquals(0,athlete1.getStart_Time());

        assertEquals(0,athlete1.getDistance_Covered(),0.0);

        assertEquals("Ashu",athlete1.getFirstName());

        assertEquals("Neema",athlete1.getLastName());

        assertEquals("M",athlete1.getGender());

        assertEquals(21,athlete1.getAge());

        athlete1.setStatus("Oncourse");
        assertEquals("Oncourse",athlete1.getStatus());

        athlete1.setStart_Time(30);
        assertEquals(30,athlete1.getStart_Time());

        athlete1.setDistance_Covered(124.564);
        assertEquals(124.564,athlete1.getDistance_Covered(),0.0);

    }

    @Test
    public void testAddClient() throws Exception{

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12027);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12028);

        athlete1.addClient(client1);
        athlete1.addClient(client2);

        assertSame(client1,athlete1.getClientList().get(0));
        assertSame(client2,athlete1.getClientList().get(1));

        assertEquals(client1,athlete1.getClientList().get(0));
        assertEquals(client2,athlete1.getClientList().get(1));
    }

    @Test
    public void testRemoveClient() throws Exception{

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12029);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12030);

        athlete1.addClient(client1);
        athlete1.addClient(client2);

        assertSame(client1,athlete1.getClientList().get(0));
        assertSame(client2,athlete1.getClientList().get(1));

        assertEquals(client1,athlete1.getClientList().get(0));
        assertEquals(client2,athlete1.getClientList().get(1));

        athlete1.removeClient(client1);
        assertEquals(client2,athlete1.getClientList().get(0));

    }

}
