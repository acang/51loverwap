package com;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: ThinkPad
 * Date: 11-10-3
 * Time: ÏÂÎç7:03
 * To change this template use File | Settings | File Templates.
 */
public class FileServer {

    public static void main(String[] args)
    {
        try
        {
            ServerSocket server = new ServerSocket(9980);
            while(true)
            {
                Socket client = server.accept();

                DataOutputStream dout = new DataOutputStream(client.getOutputStream());

                FileInputStream fin = new FileInputStream("G:\\test\\dfile.rar");

                byte[] array = new byte[1024*500];
                int len = fin.read(array,0,array.length);
                while(len > 0)
                {
                    dout.write(array,0,len);
                    dout.flush();
                    len = fin.read(array,0,array.length);
                }

                fin.close();
                dout.close();
                client.close();


            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
