package raceServer;

public class Application {
    public static void main(String[] args)
    {
        try
        {
            if(args.length!=1)
            {
                System.out.print("Local port required");
                return;
            }
            Thread t=new Thread(new TrackingServer(Integer.parseInt(args[0])));
            t.start();
        }catch(Exception e)
        {

        }
    }

}
