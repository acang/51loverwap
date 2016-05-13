import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: ThinkPad
 * Date: 11-10-8
 * Time: обнГ10:32
 * To change this template use File | Settings | File Templates.
 */
public class CServer{


    public static void main(String args[]) throws Exception
    {
       int i = 4195;
       for(int j = i ;j < i + 6;j ++)
       {
           CServerThread cst = new CServerThread(j);
           cst.start();
       }

    }
}
