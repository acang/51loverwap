import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA. User: ThinkPad Date: 11-10-8 Time: ÏÂÎç10:33 To
 * change this template use File | Settings | File Templates.
 */
public class ClienetThread extends Thread {

	DataInputStream din = null;
	DataOutputStream dout = null;

	FileOutputStream f1 = null;

	public ClienetThread(Socket client, String filename) throws Exception {
		this.din = new DataInputStream(client.getInputStream());
		this.dout = new DataOutputStream(client.getOutputStream());
		System.out.println(client.getInetAddress().getHostAddress());

		f1 = new FileOutputStream("h:\\" + filename + ".txt");

	}

	public void run() {
		try {
			byte[] head = new byte[14];
			din.readFully(head);

			String temp = "";
			for (int i = 0; i < head.length; i++) {

				String s = Integer.toHexString(head[i]).toUpperCase();
				if (s.length() > 2)
					s = s.substring(s.length() - 2);
				if (s.length() < 2)
					s = "0" + s;
				// System.out.println("l="+s.length());
				temp = temp + s;

			}

			f1.write(temp.getBytes());
			f1.write("\r\n".getBytes());

			while (true) {
				byte[] array = new byte[8];

				array[0] = (byte) 0x02;
				array[1] = 0x03;
				array[5] = 0x28;
				array[6] = 0x45;
				array[7] = (byte) 0xE7;

				dout.write(array);

				byte[] returnArray = new byte[88];
				din.readFully(returnArray);

				temp = "";
				for (int i = 0; i < returnArray.length; i++) {

					String s = Integer.toHexString(returnArray[i])
							.toUpperCase();
					if (s.length() > 2)
						s = s.substring(s.length() - 2);
					// System.out.println("l="+s.length());
					if (s.length() < 2)
						s = "0" + s;

					temp = temp + s;
				}

				f1.write(temp.getBytes());
				f1.write("\r\n".getBytes());

				array = new byte[8];

				array[0] = (byte) 0x01;
				array[1] = 0x03;
				array[5] = 0x0A;
				array[6] = (byte) 0xC5;
				array[7] = (byte) 0xCD;

				dout.write(array);

				returnArray = new byte[28];
				din.readFully(returnArray);

				temp = "";
				for (int i = 0; i < returnArray.length; i++) {

					String s = Integer.toHexString(returnArray[i])
							.toUpperCase();
					if (s.length() > 2)
						s = s.substring(s.length() - 2);
					if (s.length() < 2)
						s = "0" + s;

					// System.out.println("l="+s.length());
					temp = temp + s;
				}

				f1.write(temp.getBytes());
				f1.write("\r\n".getBytes());

				array = new byte[8];

				array[0] = (byte) 0x01;
				array[1] = 0x01;
				array[5] = 0x10;
				array[6] = (byte) 0x3D;
				array[7] = (byte) 0xC6;

				dout.write(array);

				returnArray = new byte[10];
				din.readFully(returnArray);
				temp = "";
				for (int i = 0; i < returnArray.length; i++) {

					String s = Integer.toHexString(returnArray[i])
							.toUpperCase();
					if (s.length() > 2)
						s = s.substring(s.length() - 2);
					if (s.length() < 2)
						s = "0" + s;

					// System.out.println("l="+s.length());
					temp = temp + s;
				}

				f1.write(temp.getBytes());
				f1.write("\r\n".getBytes());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
