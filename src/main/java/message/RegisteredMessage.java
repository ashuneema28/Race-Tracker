package message;

import raceData.Athlete;
import raceServer.TrackingServer;

public class RegisteredMessage extends Message {

    private int time;
    private int bibNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;


    public RegisteredMessage(int bibNumber,int time, String firstName, String lastName, String gender, int age)
    {
        this.bibNumber = bibNumber;
        this.time = time;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public void execute(TrackingServer trackingServer, int port) {
        trackingServer.sendToAllClients(this);
        Athlete athlete = new Athlete(bibNumber,firstName,lastName,gender,age,"Registered");
        trackingServer.addAthlete(athlete);
    }

    @Override
    public String toString() {
        return "Athlete"+","+String.valueOf(bibNumber) + "," + firstName + "," + lastName + "," + gender + "," + String.valueOf(age);
    }

}
