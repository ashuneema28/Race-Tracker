package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class StartedMessage extends Message {
    private int bibNumber;
    private int time;
    private String status;
    private double distance;
    private int last_updated_time;
    private int finished_time;


    public StartedMessage(int bibNumber, int time){

        this.bibNumber=bibNumber;
        this.time=time;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port) {
        for (Athlete athlete : trackingServer.getAthleteList()){
            if (athlete.getBibNumber() == bibNumber){
                athlete.setStatus("Started");
                athlete.setStart_Time(time);
                status=athlete.getStatus();
                trackingServer.sendToSubscribedClients(this, athlete);
                trackingServer.sendToAllClients(this);
                break;

            }
        }

    }

    @Override
    public String toString() {
        return "Status,"+String.valueOf(bibNumber)+","+ status + ","+ String.valueOf(time)+","+ String.valueOf(distance) +","+String.valueOf(last_updated_time)+","+ String.valueOf(finished_time);
    }
}
