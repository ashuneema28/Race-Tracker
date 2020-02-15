package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class FinishedUpdate extends Message {

    private int bibNumber;
    private int time;
    private String status;
    private int last_updated_time;

    public FinishedUpdate(int bibNumber, int time){
        this.bibNumber=bibNumber;
        this.time=time;
    }
    @Override
    public void execute(TrackingServer trackingServer, int port) {
        for (Athlete athlete : trackingServer.getAthleteList()){
            if (athlete.getBibNumber() == bibNumber){
                athlete.setStatus("Finished");
                athlete.setDistance_Covered(16090);
                athlete.setStart_Time(time);
                time=athlete.getStart_Time();
                status=athlete.getStatus();
                trackingServer.sendToSubscribedClients(this, athlete);
                trackingServer.sendToAllClients(this);
                break;
            }
        }
    }
    @Override
    public String toString() {
        return "Status,"+String.valueOf(bibNumber)+","+ status + ","+ String.valueOf(last_updated_time-30)+","+ String.valueOf(race_length) +","+String.valueOf(time)+","+ String.valueOf(time);
    }
}

