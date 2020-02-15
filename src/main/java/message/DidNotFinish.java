package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class DidNotFinish extends Message {
    private int bibNumber;
    private int time;
    private String status;
    private double distance;
    private int last_updated_time;
    private int finished_time;


    public DidNotFinish(int bibNumber, int time){

        this.bibNumber=bibNumber;
        this.time=time;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port) {
        for (Athlete athlete : trackingServer.getAthleteList()){
            if (athlete.getBibNumber() == bibNumber){
                athlete.setStatus("DidNotFinish");
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
        return "Status,"+String.valueOf(bibNumber)+","+ status + ","+ String.valueOf(last_updated_time-30)+","+ String.valueOf(race_length) +","+String.valueOf(time)+","+ String.valueOf(finished_time);
    }
}