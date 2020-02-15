package message;

import raceData.Athlete;
import raceData.Client;
import raceServer.TrackingServer;

public class UnsubscribeMessage extends Message {

    private int bibNumber;

    public UnsubscribeMessage(int bibNumber){
        this.bibNumber = bibNumber;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port) {
        trackingServer.getAthleteByBibNumber(bibNumber).removeClient(trackingServer.getClientByPortNumber(port));
        /*for(Client client : trackingServer.getClientList()) {
            if (client.getPort() == port) {
                for (Athlete athlete : trackingServer.getAthleteList()) {
                    if (athlete.getBibNumber() == bibNumber){
                        athlete.removeClient(client);
                    }
                }
            }
        }*/

    }
}

