import message.DummyMessageProcessor;
import message.Message;
import org.junit.Test;
import raceData.Athlete;
import raceData.Client;
import raceServer.Communicator;
import raceServer.TrackingServer;

import java.net.InetAddress;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrackingServerTest {

    @Test
    public void testConstruction() throws Exception{
        int localPort = 12005;
        TrackingServer trackingServer= new TrackingServer(localPort);
        Communicator communicator= trackingServer.getCommunicator();
        assertTrue(communicator.getLocalPort()>0);

        DummyMessageProcessor processor = trackingServer.getDummyMessageProcessor();
        communicator.setProcessor(processor);
        assertSame(processor,communicator.getProcessor());

        communicator.close();
        assertEquals(0,communicator.getLocalPort());

    }


    @Test
    public void testAddClients() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12006);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12007);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12008);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        assertEquals(12007,trackingServer.getClientList().get(0).getPort());
        assertEquals(12008,trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientList().get(1).getAddress());

        assertEquals(12007,trackingServer.getClientByPortNumber(12007).getPort());
        assertEquals(12008,trackingServer.getClientByPortNumber(12008).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12007).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12008).getAddress());

        Client client3 = trackingServer.getClientByPortNumber(12009);
        assertEquals(null,client3);

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testAddAthletes() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12010);
        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");
        Athlete athlete2 = new Athlete(2,"Joya", "Mehta", "F", 29, "Registered");

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        assertEquals("Registered",trackingServer.getAthleteList().get(0).getStatus());
        assertEquals("Registered",trackingServer.getAthleteList().get(1).getStatus());

        assertEquals(1,trackingServer.getAthleteList().get(0).getBibNumber());
        assertEquals(2,trackingServer.getAthleteList().get(1).getBibNumber());

        assertEquals(0,trackingServer.getAthleteList().get(0).getStart_Time());
        assertEquals(0,trackingServer.getAthleteList().get(1).getStart_Time());

        assertEquals(0,trackingServer.getAthleteList().get(0).getDistance_Covered(),0.0);
        assertEquals(0,trackingServer.getAthleteList().get(0).getDistance_Covered(),0.0);

        assertEquals(1,trackingServer.getAthleteByBibNumber(1).getBibNumber());
        assertEquals(0,trackingServer.getAthleteByBibNumber(1).getStart_Time());
        assertEquals(0,trackingServer.getAthleteByBibNumber(1).getDistance_Covered(),0.0);

        assertEquals(2,trackingServer.getAthleteByBibNumber(2).getBibNumber());
        assertEquals(0,trackingServer.getAthleteByBibNumber(2).getStart_Time());
        assertEquals(0,trackingServer.getAthleteByBibNumber(2).getDistance_Covered(),0.0);

        Athlete athlete3 = trackingServer.getAthleteByBibNumber(3);
        assertEquals(null,athlete3);

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testSendToAllClients() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12011);

        Communicator comm1 = new Communicator(12012);
        Communicator comm2 = new Communicator(12013);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12014);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12015);

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        assertEquals(12014,trackingServer.getClientList().get(0).getPort());
        assertEquals(12015,trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientList().get(1).getAddress());

        assertEquals(12014,trackingServer.getClientByPortNumber(12014).getPort());
        assertEquals(12015,trackingServer.getClientByPortNumber(12015).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12014).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12015).getAddress());

        Message message = Message.createObject("Registered,1,0,Valentine,Zamora,M,30");
        trackingServer.sendToAllClients(message);

        try{
            Message message1 = Message.createObject(null);
            trackingServer.sendToAllClients(message1);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals(null, e.getMessage());
        }

        trackingServer.getCommunicator().close();

        TrackingServer trackingServer1 = new TrackingServer(12016);
        trackingServer.sendToAllClients(message);

    }

    @Test
    public void testSendToSubscribedClients() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12017);

        Communicator comm1 = new Communicator(12018);
        Communicator comm2 = new Communicator(12019);

        Client client1 = new Client(InetAddress.getLocalHost().getHostAddress(), 12020);
        Client client2 = new Client(InetAddress.getLocalHost().getHostAddress(), 12021);

        Athlete athlete1 = new Athlete(1,"Ashu", "Neema", "M", 21, "Registered");
        Athlete athlete2 = new Athlete(2,"Joya", "Mehta", "F", 29, "Registered");

        trackingServer.addClient(client1);
        trackingServer.addClient(client2);

        trackingServer.addAthlete(athlete1);
        trackingServer.addAthlete(athlete2);

        athlete1.addClient(client1);
        athlete1.addClient(client2);
        athlete2.addClient(client1);

        assertEquals(12020,trackingServer.getClientList().get(0).getPort());
        assertEquals(12021,trackingServer.getClientList().get(1).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientList().get(0).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientList().get(1).getAddress());

        assertEquals(12020,trackingServer.getClientByPortNumber(12020).getPort());
        assertEquals(12021,trackingServer.getClientByPortNumber(12021).getPort());

        assertEquals(InetAddress.getByName(client1.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12020).getAddress());
        assertEquals(InetAddress.getByName(client2.getAddress()).getHostAddress(),trackingServer.getClientByPortNumber(12021).getAddress());

        assertEquals("Registered",trackingServer.getAthleteList().get(0).getStatus());
        assertEquals("Registered",trackingServer.getAthleteList().get(1).getStatus());

        assertEquals(1,trackingServer.getAthleteList().get(0).getBibNumber());
        assertEquals(2,trackingServer.getAthleteList().get(1).getBibNumber());

        assertEquals(0,trackingServer.getAthleteList().get(0).getStart_Time());
        assertEquals(0,trackingServer.getAthleteList().get(1).getStart_Time());

        assertEquals(0,trackingServer.getAthleteList().get(0).getDistance_Covered(),0.0);
        assertEquals(0,trackingServer.getAthleteList().get(0).getDistance_Covered(),0.0);

        assertEquals(1,trackingServer.getAthleteByBibNumber(1).getBibNumber());
        assertEquals(0,trackingServer.getAthleteByBibNumber(1).getStart_Time());
        assertEquals(0,trackingServer.getAthleteByBibNumber(1).getDistance_Covered(),0.0);

        assertEquals(2,trackingServer.getAthleteByBibNumber(2).getBibNumber());
        assertEquals(0,trackingServer.getAthleteByBibNumber(2).getStart_Time());
        assertEquals(0,trackingServer.getAthleteByBibNumber(2).getDistance_Covered(),0.0);

        Message message = Message.createObject("Registered,3,0,Valentine,Zamora,M,30");
        trackingServer.sendToSubscribedClients(message,athlete1);
        trackingServer.sendToSubscribedClients(message,athlete2);

        try{
            Message message1 = Message.createObject(null);
            trackingServer.sendToAllClients(message1);
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals(null, e.getMessage());
        }

        trackingServer.getCommunicator().close();
    }

    @Test
    public void testStartOfServer() {
        try{
            Thread t=new Thread(new TrackingServer(12022));
            t.start();
        }
        catch(Exception e){

        }
        try{
            Thread t=new Thread(new TrackingServer(-12));
            t.start();
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("port out of range:-12", e.getMessage());
        }
        try{
            Thread t=new Thread(new TrackingServer(12022));
            t.start();
            fail("Expected exception not thrown");
        }
        catch(Exception e){
            assertEquals("Address already in use: Cannot bind", e.getMessage());
        }

    }

}
