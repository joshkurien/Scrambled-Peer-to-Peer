package Main;


import java.security.KeyPair;
import java.security.PublicKey;

import Crypt.ecdhe;
import socket.Scramble;

public class Reciever {
	static private KeyPair keys;
	
	public static void main(String args[]) throws Exception
	{
		String shared_key,message;
		
	/*	Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter port number:");
		port = stdin.nextInt();*/
		
		keys = ecdhe.keygen();
		
		PublicKey remote = socket.keyreciev.k_recieve(keys.getPublic());
		
		shared_key = ecdhe.shared_secret(remote,keys);
	//	System.out.println(shared_key);
		message = 	Scramble.recieved(shared_key);
		System.out.println(message);
	}	
}
