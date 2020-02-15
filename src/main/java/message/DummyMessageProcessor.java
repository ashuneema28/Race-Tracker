package message;

import raceServer.TrackingServer;

import java.net.InetAddress;
import java.net.SocketException;

public class DummyMessageProcessor implements IMessageProcessor {

    private String name;
    private int receiveCount=0;
    TrackingServer trackingServer;

    public DummyMessageProcessor(String name,TrackingServer trackingServer) {
        this.name = name;
        this.trackingServer=trackingServer;
    }
    @Override
    public void process(String message, InetAddress address, int port) {
        if (message==null) {
            System.out.println("Null string");
            return;
        }

        if (address==null) {
            System.out.println("Null address");
            return;
        }

        System.out.println(String.format("%s received: %s from %s:%d", name, message, address.toString(), port));

        String inetAddress=address.toString();
        inetAddress=inetAddress.substring(1);
        Message message1= Message.createObject(message+","+inetAddress+","+String.valueOf(port));

        if(message1!=null) {
            try {
                message1.execute(trackingServer,port);
            }
            catch (SocketException e) {
                e.printStackTrace();

            }
        }
        receiveCount++;
    }

    public int ReceiveCount() {
        return receiveCount; }
}
