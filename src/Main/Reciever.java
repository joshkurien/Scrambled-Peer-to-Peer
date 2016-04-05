package Main;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import socket.ser;

public class Reciever {
	public static void main(String args[]) throws IOException
	{
		int port;
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Enter port number:");
		port = stdin.nextInt();
		
		System.out.println(ser.recieved(port));
	}	
}
