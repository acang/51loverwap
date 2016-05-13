import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: ThinkPad
 * Date: 11-10-3
 * Time: œ¬ŒÁ7:14
 * To change this template use File | Settings | File Templates.
 */
public class FileClient {

    public static void main(String args[])
    {
        try
        {
            Socket client = new Socket("61.155.107.6",9980);
            DataInputStream din = new DataInputStream(client.getInputStream());
            FileOutputStream fout = new FileOutputStream("e:/dfile.rar");
            int max = 0;
            byte[] array = new byte[1024*1024];
            int len = din.read(array,0,array.length);
            while(len > 0)
            {
                max = max + len;
                fout.write(array,0,len);
                fout.flush();
                len = din.read(array,0,array.length);
            }


            System.out.println("∂¡»°"+max);
            din.close();
            fout.close();
            client.close();

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
