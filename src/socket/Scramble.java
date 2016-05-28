package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

import quanatisation.assimilation;
import quanatisation.division;;

public class Scramble {
	private static int pdecideSend(String IP,int no) throws UnknownHostException, IOException, InterruptedException
	{
		int pNo;
		Socket s ;
		while(true) {
			try{
				s = new Socket(IP, 51324);
				if(s != null) break;
			}
			catch(IOException e) { Thread.sleep(1000); }
		}
		Scanner netsc = new Scanner(s.getInputStream());
		

		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(no);
		pNo = netsc.nextInt();
		
		s.close();
		return pNo;		
	}
	
	public static void msend(String message, String finalIP, int no_ports,String key) throws UnknownHostException, IOException, InterruptedException
	{
		char divided[][];
		int pNo = pdecideSend(finalIP,no_ports);
		
		divided = division.scramble(key, message, no_ports);
		
		for(int i = 0;i<no_ports;i++)
		{
			cli.msend(new String(divided[i]), finalIP, pNo);
			pNo++;
		}
	}
	
	private static int[] pdecideRec() throws IOException
	{
		int ports[] = new int[2];
		Random r = new Random();
		ServerSocket s1 = new ServerSocket(51324);
		Socket ss = s1.accept();
		Scanner sc =new Scanner(ss.getInputStream());
		ports[1] = sc.nextInt();
		ports[0] = r.nextInt(64510 - ports[1]);
		ports[0] += 1025;
		
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(ports[0]);
		
		ss.close();
		s1.close();
		return(ports);
	}
	public static String recieved(String key) throws IOException
	{
		String temp = null,result;
		char scrambled[][];
		int i,ports[] = pdecideRec();
		
		scrambled = new char[ports[1]][];
		
		for(i=0;i<ports[1];i++)
		{
			temp = ser.recieved(ports[0]);
		//	System.out.println(temp);
			scrambled[i] = temp.toCharArray();
		//	System.out.println(scrambled[i]);
			ports[0]++;
		}
		result = assimilation.assemble(key, scrambled, temp.length(), ports[1]);
		
		return(result);
	}
}
