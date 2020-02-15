package message;

import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

import java.net.SocketException;

public class HelloMessage  extends Message {
    private String clientAddress;
    private int clientPort;
    public HelloMessage(String clientAddress, int clientPort)
    {
        this.clientAddress=clientAddress;
        this.clientPort=clientPort;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port){
        Client c=new Client(clientAddress,clientPort);
        trackingServer.addClient(c);
        if (flag==1){
            Message message = Message.createObject(race_message);
            try {
                if (message != null) {
                    message.execute(trackingServer,port);
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        if(flag2==1){
            for(Athlete athlete:trackingServer.getAthleteList()){
                Message message = Message.createObject("Registered,"+ String.valueOf(athlete.getBibNumber())+",0,"+athlete.getFirstName()+","+athlete.getLastName()+","+athlete.getGender()+","+String.valueOf(athlete.getAge()));
                trackingServer.sendToAllClients(message);
            }
        }
    }
}
