import message.DummyMessageProcessor;
import org.junit.Test;
import raceServer.Communicator;
import raceServer.TrackingServer;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class DummyMessageProcessorTest {

    @Test
    public void testConstruction() throws Exception{
        TrackingServer trackingServer = new TrackingServer(12023);
        Communicator communicator = trackingServer.getCommunicator();
        String name = "A";
        DummyMessageProcessor dummyMessageProcessor = new DummyMessageProcessor(name,trackingServer);

        communicator.setProcessor(dummyMessageProcessor);
        assertSame(dummyMessageProcessor, communicator.getProcessor());

        assertTrue(communicator.getLocalPort()>0);

        communicator.close();
        assertEquals(0, communicator.getLocalPort());
    }

    @Test
    public void testProcess() throws Exception{
        String message1 = "Registered,1,0,Valentine,Zamora,M,30";
        String message2 = "Registered,2,0,Nimbu,Bilal,M,30";
        String message3 = "Registered,3,0,Pranay,Summons,F,30";
        String message4 = "Registered,4,0,Aparna,Setwet,F,30";
        TrackingServer trackingServer = new TrackingServer(12024);
        Communicator communicator = trackingServer.getCommunicator();
        DummyMessageProcessor dummyMessageProcessor = new DummyMessageProcessor("A",trackingServer);

        communicator.setProcessor(dummyMessageProcessor);
        assertSame(dummyMessageProcessor, communicator.getProcessor());

        assertTrue(communicator.getLocalPort()>0);

        dummyMessageProcessor.process(message1, InetAddress.getLocalHost(), communicator.getLocalPort());
        dummyMessageProcessor.process(message2, InetAddress.getLocalHost(), communicator.getLocalPort());
        dummyMessageProcessor.process(message3, InetAddress.getLocalHost(), communicator.getLocalPort());
        dummyMessageProcessor.process(message4, InetAddress.getLocalHost(), communicator.getLocalPort());

        assertTrue(dummyMessageProcessor.ReceiveCount()>0);

        assertEquals(4, dummyMessageProcessor.ReceiveCount());

        dummyMessageProcessor.process(null, InetAddress.getLocalHost(), communicator.getLocalPort());
        dummyMessageProcessor.process(message2, null, communicator.getLocalPort());

    }
}
