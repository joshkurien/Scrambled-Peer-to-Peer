package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ser {
	
	public static String recieved(int portNo) throws IOException
	{
		String temp,fin = null;
		ServerSocket s1 = null;
		try{
			s1 = new ServerSocket(portNo);
			Socket ss = s1.accept();
			
	//		System.out.println("Connected");
			

            BufferedReader in = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            
            fin = in.readLine();
            while((temp = in.readLine())!=null)
            {
            	fin = fin + "\n";
            	fin = fin + temp;
            }

            /*
			PrintStream p = new PrintStream(ss.getOutputStream());
			p.println("Recieved row");
			*/
			in.close();
			ss.close();
			s1.close();
		} catch (Exception e)
        {
            e.printStackTrace();
        }
		
		return(fin);
	}

}
