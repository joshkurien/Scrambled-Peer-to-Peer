package Main;

import java.io.IOException;
import java.io.PrintStream;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

import Crypt.ecdhe;
import socket.ser;

public class Reciever {
	static private KeyPair keys;
	
	public static void main(String args[]) throws Exception
	{
		int port;
		String shared_key;
		
	/*	Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter port number:");
		port = stdin.nextInt();*/
		
		keys = ecdhe.keygen();
		
		PublicKey remote = socket.keyreciev.k_recieve(keys.getPublic());
		
		shared_key = ecdhe.shared_secret(remote,keys);
		System.out.println(shared_key);
		
	//	System.out.println(ser.recieved(port));
	}	
}
