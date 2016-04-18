package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cli {

	public static void msend(String message, String finalIP, int portNo) throws UnknownHostException, IOException
	{
		String temp;
		Socket s = new Socket(finalIP, portNo);
		Scanner netsc = new Scanner(s.getInputStream());
		

		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(message);
		temp = netsc.nextLine();
		if(!temp.contentEquals("Recieved row"))
			System.out.println("Some error");
	}
}
