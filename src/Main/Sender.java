package Main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

import Crypt.ecdhe;
import socket.cli;
import socket.keysend;

public class Sender {
	
	static private KeyPair keys;
	
	static void messageSend(String IP, int port) throws UnknownHostException, IOException
	{
		String message;
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter Message:");
		message = stdin.nextLine();
		
		cli.msend(message, IP, port);
	}
	
	public static void main(String args[]) throws Exception
	{
		String sendIP;
		int port;
		PublicKey remote;
	
		Scanner stdin = new Scanner(System.in);
		
		keys = ecdhe.keygen();
	
	/*	Scanner stdin2 = new Scanner(System.in);
		System.out.println("Enter port to be used");
		port = stdin2.nextInt();*/
		
		System.out.println("Enter  Destination IP");
		sendIP = stdin.nextLine();
		
		remote = socket.keysend.k_send(keys.getPublic(),sendIP);
		
		System.out.println(ecdhe.shared_secret(remote,keys));
	//	messageSend(sendIP,port);
	}
}
