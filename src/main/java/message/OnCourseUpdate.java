package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class OnCourseUpdate extends Message {

    private int bibNumber;
    private int time;
    private String status;
    private double distance;
    private int last_updated_time;
    private int finished_time;



    public OnCourseUpdate(int bibNumber, int time, double distance){
        this.bibNumber=bibNumber;
        this.time=time;
        this.distance=distance;
    }

    @Override
    public void execute(TrackingServer trackingServer, int port) {
        for (Athlete athlete : trackingServer.getAthleteList()){
            if (athlete.getBibNumber() == bibNumber){
                athlete.setStatus("OnCourse");
                athlete.setDistance_Covered(distance);
                athlete.setStart_Time(time);
                time=athlete.getStart_Time();
                status=athlete.getStatus();
                distance=athlete.getDistance_Covered();
                trackingServer.sendToSubscribedClients(this, athlete);
                trackingServer.sendToAllClients(this);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Status,"+String.valueOf(bibNumber)+","+ status + ","+ String.valueOf(last_updated_time-30)+","+ String.valueOf(distance) +","+String.valueOf(time)+","+ String.valueOf(time-30);
    }
}