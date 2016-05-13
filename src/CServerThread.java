import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: ThinkPad
 * Date: 11-10-8
 * Time: ÏÂÎç11:45
 * To change this template use File | Settings | File Templates.
 */
public class CServerThread extends Thread {

    int port;

    public CServerThread(int port)
    {
        this.port = port;
    }

    public void run()
    {
        try
        {
        ServerSocket server = new ServerSocket(port);
         int i =0;
        while(true)
        {
            Socket client = server.accept();
            ClienetThread ct = new ClienetThread(client,port+"_"+i);
            i = i +1;
            ct.start();

        }
        }catch (Exception e)
        {

        }
    }
}
