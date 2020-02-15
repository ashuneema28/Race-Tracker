package raceServer;

import message.DummyMessageProcessor;
import message.Message;
import raceData.Athlete;
import raceData.Client;

import java.util.ArrayList;
import java.net.*;

public class TrackingServer implements Runnable {

    private ArrayList<Athlete> athletes = new ArrayList<Athlete>();
    private ArrayList<Client> clients;
    private Communicator communicator=null;
    private DummyMessageProcessor dummyMessageProcessor=null;


    public TrackingServer(int localPort) throws SocketException
    {
        communicator = new Communicator(localPort);
        dummyMessageProcessor=new DummyMessageProcessor("raceServer.TrackingServer",this);
        communicator.setProcessor(dummyMessageProcessor);
        clients = new ArrayList<Client>();
    }

    public Communicator getCommunicator(){return communicator;}

    public DummyMessageProcessor getDummyMessageProcessor(){return dummyMessageProcessor;}

    public void addClient(Client c)
   {
       clients.add(c);
   }

    public void addAthlete(Athlete athlete)
    {
        athletes.add(athlete);
    }

    public void sendToAllClients(Message message)
    {
       for(Client c: clients)
       {
           try {
               communicator.send(message.toString(),InetAddress.getByName(c.getAddress()),c.getPort());
           }

           catch (Exception e) {
               System.out.println("Exception :"+ e);

           }
       }

    }

   public void sendToSubscribedClients(Message message, Athlete athlete){
       for(Client c: athlete.getClientList()){
           try{
               communicator.send(message.toString(),InetAddress.getByName(c.getAddress()),c.getPort());
           }
           catch(Exception e){
               System.out.println("Exception : "+ e);

           }
       }

   }

   public ArrayList<Athlete> getAthleteList(){
       return athletes;
   }

   public Athlete getAthleteByBibNumber(int bibNumber){
       for (Athlete athlete : athletes){
           if (athlete.getBibNumber() == bibNumber){
            return athlete;
           }
       }
       return null;
   }

    public Client getClientByPortNumber(int portNumber){
        for (Client client : clients){
            if (client.getPort() == portNumber){
                return client;
            }
        }
        return null;
    }

   public ArrayList<Client> getClientList(){
        return clients;
    }

   public void run() {
       communicator.start();
    }

}
