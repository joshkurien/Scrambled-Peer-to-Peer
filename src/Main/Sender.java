package Main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Scanner;

import Crypt.ecdhe;
import socket.Scramble;
import socket.keysend;

public class Sender {
	
	static private KeyPair keys;
	
	static void messageSend(String IP, int port,String key) throws UnknownHostException, IOException, InterruptedException
	{
		String message;
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter Message:");
		message = stdin.nextLine();
		
		Scramble.msend(message, IP, port, key);
	}
	
	public static void main(String args[]) throws Exception
	{
		String sendIP;
		int ports;
		PublicKey remote;
	
		Scanner stdin = new Scanner(System.in);
		
		keys = ecdhe.keygen();
	
		Scanner stdin2 = new Scanner(System.in);
		System.out.println("Enter number of ports to be used");
		ports = stdin2.nextInt();
		
		System.out.println("Enter  Destination IP");
		sendIP = stdin.nextLine();
		
		remote = keysend.k_send(keys.getPublic(),sendIP);
		
		String shared_key = ecdhe.shared_secret(remote,keys);
		messageSend(sendIP,ports,shared_key);
	}
	
	public static void work(int ports, String sendIP, String message) throws Exception{
		PublicKey remote;
		
		keys = ecdhe.keygen();
	
		remote = keysend.k_send(keys.getPublic(),sendIP);
		
		String shared_key = ecdhe.shared_secret(remote,keys);
		
		Scramble.msend(message, sendIP, ports, shared_key);
	}
}
