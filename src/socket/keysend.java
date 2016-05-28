package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Scanner;

import Crypt.key_string;

public class keysend {
	public static PublicKey k_send(PublicKey key, String finalIP) throws UnknownHostException, IOException, GeneralSecurityException, InterruptedException
	{
		String temp;
		Socket s;
		temp = key_string.savePublicKey(key);
		while(true) {
			try{
				s = new Socket(finalIP, 7013);
				if(s != null) break;
			}
			catch(IOException e) { Thread.sleep(1000); }
		}
		
		Scanner netsc = new Scanner(s.getInputStream());
		

		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(temp);

		temp = netsc.next();
			
		return(key_string.loadPublicKey(temp));
	}
}
