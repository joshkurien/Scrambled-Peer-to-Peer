package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cli {

	public static void msend(String message, String finalIP, int portNo) throws UnknownHostException, IOException
	{
	//	String temp;
		PrintWriter out;
		try{
				Socket s = new Socket(finalIP, portNo);
		//		Scanner netsc = new Scanner(s.getInputStream());
				
				out = new PrintWriter(s.getOutputStream(), true);
				
				out.print(message+"\r\n");
				out.flush();
				out.close();
				/*
				temp = netsc.nextLine();
				if(!temp.contentEquals("Recieved row"))
					System.out.println("Some error");
				
				netsc.close();	*/
				s.close();
		} catch (IOException e)
        {
            e.printStackTrace();
        }
	}
}
