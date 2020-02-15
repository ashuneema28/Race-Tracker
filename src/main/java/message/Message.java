package message;
import raceServer.TrackingServer;

import java.net.InetAddress;
import java.net.SocketException;

public abstract class Message {

    static int flag=0;
    static int flag2=0;
    static String race_message = null;
    static int race_length=0;

    public abstract void execute(TrackingServer trackingServer, int port) throws SocketException;

        public static Message createObject(String message)
        {
            String [] params= message.split(",");

            String messageName=params[0];
                if(messageName.equals("Hello"))
                {
                    return new HelloMessage(params[1],Integer.parseInt(params[2]));
                }
                if(messageName.equals("Race")) {
                    flag=1;
                    race_message=message;
                    race_length = Integer.parseInt(params[2]);
                    return new RaceStartedMessage(params[1],Integer.parseInt(params[2]));
                }
                if(messageName.equals("Registered")) {
                    flag2=1;
                    return new RegisteredMessage(Integer.parseInt(params[1]),Integer.parseInt(params[2]),params[3],params[4],params[5],Integer.parseInt(params[6]));
                }
                if(messageName.equals("Subscribe")) {
                    return new SubscribeMessage(Integer.parseInt(params[1]));
                }
                if(messageName.equals("Started")) {
                    return new StartedMessage(Integer.parseInt(params[1]),Integer.parseInt(params[2]));
                }
                if(messageName.equals("Unsubscribe")) {
                    return new UnsubscribeMessage(Integer.parseInt(params[1]));
                }
                if(messageName.equals("OnCourse")) {
                    return new OnCourseUpdate(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Double.parseDouble(params[3]));
                }
                if(messageName.equals("Finished")) {
                    return new FinishedUpdate(Integer.parseInt(params[1]),Integer.parseInt(params[2]));
                }
                if(messageName.equals("DidNotFinish")) {
                    return new DidNotFinish(Integer.parseInt(params[1]),Integer.parseInt(params[2]));
                }
                if(messageName.equals("DidNotStart")) {
                    return new DidNotStart(Integer.parseInt(params[1]),Integer.parseInt(params[2]));
                }
                return null;
        }
}
