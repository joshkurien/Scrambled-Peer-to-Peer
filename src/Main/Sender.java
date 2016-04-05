package Main;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import socket.cli;

public class Sender {
	
	static void messageSend(String IP, int port) throws UnknownHostException, IOException
	{
		String message;
		Scanner stdin = new Scanner(System.in);
		message = stdin.nextLine();
		
		cli.msend(message, IP, port);
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		String sendIP;
		int port;
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter port to be used");
		port = stdin.nextInt();
		
		System.out.println("Enter  Destination IP");
		sendIP = stdin.nextLine();
		
		messageSend(sendIP,port);
	}
}
