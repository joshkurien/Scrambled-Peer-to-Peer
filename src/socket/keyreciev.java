package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.Scanner;

import Crypt.key_string;

public class keyreciev {
	public static PublicKey k_recieve(PublicKey key) throws IOException, GeneralSecurityException
	{
		String temp;
		ServerSocket s1 = new ServerSocket(1734);
		Socket ss = s1.accept();
		Scanner sc =new Scanner(ss.getInputStream());
		
		temp = sc.next();	
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(key_string.savePublicKey(key));
		
		
		return(key_string.loadPublicKey(temp));
	}
}
