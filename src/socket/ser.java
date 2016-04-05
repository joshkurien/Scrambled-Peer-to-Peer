package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ser {
	
	public static String recieved(int portNo) throws IOException
	{
		String temp;
		ServerSocket s1 = new ServerSocket(portNo);
		Socket ss = s1.accept();
		Scanner sc =new Scanner(ss.getInputStream());
		temp = sc.next();
		
		
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println("Recieved Message");
		
		return(temp);
	}

}
