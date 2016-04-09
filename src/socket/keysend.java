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
	public static PublicKey k_send(PublicKey key, String finalIP) throws UnknownHostException, IOException, GeneralSecurityException
	{
		String temp;
		temp = key_string.savePublicKey(key);
		Socket s = new Socket(finalIP, 1734);
		Scanner netsc = new Scanner(s.getInputStream());
		

		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(temp);

		temp = netsc.next();
			
		return(key_string.loadPublicKey(temp));
	}
}
