package raceData;

import java.util.Observable;
import java.util.ArrayList;
import java.util.List;

public class Athlete extends Observable {

    private int bibNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String status;
    private double distance_Covered;
    private int start_Time;
    private double finish_Time;
    private double last_Updated_Time;
    private ArrayList<Client> clients = new ArrayList<>();


    public Athlete(int bibNumber,String firstName, String lastName, String gender, int age, String status){
        this.bibNumber = bibNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public int getAge(){return age;}

    public int getBibNumber(){ return bibNumber; }

    public String getGender(){return gender;}

    public int getStart_Time(){ return start_Time ;}

    public double getDistance_Covered(){
        return distance_Covered;
    }

    public ArrayList<Client> getClientList(){return clients;}

    public void setStart_Time(int start_Time){
        this.start_Time=start_Time;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setDistance_Covered(double distance_Covered){
        this.distance_Covered = distance_Covered;
    }

    public void addClient(Client subscriber){
        clients.add(subscriber);
    }

    public void removeClient(Client subscriber){
        clients.remove(subscriber);
    }
}
