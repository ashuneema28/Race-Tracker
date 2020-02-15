package message;

import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

public class SubscribeMessage extends Message {

    private int bibNumber;

    public SubscribeMessage(int bibNumber){
        this.bibNumber = bibNumber;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port) {

        trackingServer.getAthleteByBibNumber(bibNumber).addClient(trackingServer.getClientByPortNumber(port));

    }
}
